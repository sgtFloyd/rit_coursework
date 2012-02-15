  /* factorial.rex -- computes the factorial of a number */
  /* Usage: exec factorial number */
  /* Example: exec factorial 5 */

  arg N .

  call factorial N

  say result

  exit 0 /* don't fall through to the PROCEDURE instruction */

  /* internal procedure FACTORIAL
   * returns factorial of argument N
   */

  factorial : PROCEDURE

  n = arg( 1 )

  if n  = 1 then
    return 1
   
  return n * factorial( n - 1 )