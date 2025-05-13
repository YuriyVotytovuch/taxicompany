function openModal() {
    document.getElementById('orderModal').style.display = 'flex';
  }
  function closeModal() {
    document.getElementById('orderModal').style.display = 'none';
  }
  function submitForm() {
    alert('Ваше замовлення прийнято! Ми зв’яжемося з вами.');
    closeModal();
  }