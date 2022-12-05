export function phoneValidator(phone) {
  if (!phone) return "Phone number can't be empty."
  if (phone.length != 10) return 'Phone number must be complete'
  return ''
}