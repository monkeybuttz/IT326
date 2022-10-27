export function phoneValidator(password) {
  if (!password) return "Phone number can't be empty."
  if (password.length != 5) return 'Phone number must be complete'
  return ''
}