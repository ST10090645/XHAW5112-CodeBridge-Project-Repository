// Calculates fees and sends an auto-reply email to users who "successfully" make a booking 

document.addEventListener("DOMContentLoaded", () => {
  // URL for the web app on Google Apps Script 
  const BOOKING_ENDPOINT = "https://script.google.com/macros/s/AKfycbxuGl5DmuxS9OlqXGYxLHFXX0jGzdyyRfyEUK14F1rMnkYWq9yZ4T4QVHnk7Jtx_mFF/exec"; // e.g. https://script.google.com/macros/s/XXXX/exec

  const calcBtn        = document.getElementById("calcBtn");
  const bookingBtn     = document.getElementById("bookingBtn");
  const output         = document.getElementById("output");

  const firstNameInput = document.getElementById("firstName");
  const lastNameInput  = document.getElementById("lastName");
  const ageInput       = document.getElementById("age");
  const emailInput     = document.getElementById("email");
  const phoneInput     = document.getElementById("phone");

  const courseBoxes    = Array.from(document.querySelectorAll(".course"));

  const ZAR = new Intl.NumberFormat("en-ZA", { minimumFractionDigits: 0 });
  const val = (el) => (el && typeof el.value === "string" ? el.value.trim() : "");

  function parseCheckedCourses() {
    const selected = [];
    let baseTotal = 0;

    courseBoxes.forEach((box) => {
      if (box.checked) {
        const [name, feeStr] = String(box.value).split("|");
        const fee = parseInt(feeStr, 10) || 0;
        baseTotal += fee;
        selected.push({ name: name.trim(), fee });
      }
    });

    return { selected, baseTotal };
  }

  function computeDiscountRate(count) {
    if (count >= 4) return 0.15;
    if (count === 3) return 0.10;
    if (count === 2) return 0.05;
    return 0;
  }

  function assembleQuoteObject() {
    const { selected, baseTotal } = parseCheckedCourses();
    const discountRate   = computeDiscountRate(selected.length);
    const discountAmount = Math.round(baseTotal * discountRate);
    const finalTotal     = baseTotal - discountAmount;

    return { selected, baseTotal, discountRate, discountAmount, finalTotal };
  }

  function buildQuoteText(quote) {
    const { selected, baseTotal, discountRate, discountAmount, finalTotal } = quote;
    const lines = [];

    lines.push("Registered Courses:");
    if (selected.length === 0) lines.push("— none selected —");
    else selected.forEach((c) => lines.push(`• ${c.name}`));
    lines.push("");

    lines.push(`Base Total: R${ZAR.format(baseTotal)}`);
    lines.push(
      `Discount: ${Math.round(discountRate * 100)}% ` +
      (discountRate > 0 ? `(-R${ZAR.format(discountAmount)})` : "(R0)")
    );
    lines.push(`Final Total: R${ZAR.format(finalTotal)}`);
    lines.push("");

    return lines.join("\n");
  }

  // Calculate fees 
  function calculateAndRenderQuote() {
    const quoteObj  = assembleQuoteObject();
    const quoteText = buildQuoteText(quoteObj);
    if (output) output.value = quoteText;
    return quoteObj;
  }

  if (calcBtn) {
    calcBtn.addEventListener("click", (e) => {
      e.preventDefault();
      calculateAndRenderQuote();
    });
  }

  // Ensuring that the user's email is valid 
  function isValidEmail(value) {
   
    if (emailInput && emailInput.type === "email") return emailInput.checkValidity();
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(String(value || "").trim());
  }

  // Auto-reply feature 
  async function sendUserAutoReply(name, toEmail) {
   
    const payload = { name, to: toEmail };
    const body = new URLSearchParams({ payload: JSON.stringify(payload) });

    const res = await fetch(BOOKING_ENDPOINT, {
      method: "POST",
      body 
    });

    try {
      const data = await res.json();
      return !!data.ok;
    } catch {
  
      return res.ok;
    }
  }

  if (bookingBtn) {
    bookingBtn.addEventListener("click", async (e) => {
      e.preventDefault();

      const first = val(firstNameInput);
      const email = val(emailInput);

      if (!email || !isValidEmail(email)) {
        if (emailInput && emailInput.type === "email") {
          emailInput.reportValidity();
        } else {
          alert("Please enter a valid email address so we can send confirmation.");
          emailInput && emailInput.focus();
        }
        return;
      }

      const originalText = bookingBtn.textContent;
      bookingBtn.textContent = "Sending…";
      bookingBtn.disabled = true;

      try {
        const ok = await sendUserAutoReply(first || "there", email);
        if (ok) {
          alert(`Thanks, ${first || "there"}! We've emailed a confirmation to ${email}.`);
        } else {
          alert("Your booking was received, but we couldn't send the confirmation email. We’ll be in touch soon.");
        }
      } catch (err) {
        alert("Sorry, we couldn't send the confirmation email right now. Please try again later.");
      } finally {
        bookingBtn.textContent = originalText;
        bookingBtn.disabled = false;
      }
    });
  }
});
