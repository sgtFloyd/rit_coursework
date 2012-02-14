;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; file name: kayles.ss
; author: Gabriel Smith (ges7506)
;
; description:
;  see www.cs.rit.edu/~zjb/courses/ai/proj1.html for full project write-up
;
;  state representation:
;    list of integers with each value corresponding to a group of pins
;    example: the list (3 4 1 2 2 3) would be equal to the pin configuration:
;             |||  ||||  |  ||  ||  |||
;
;  successor function:
;    will return a list of all the possible next moves, accounting for symmerty.
;    example: the states (1 2 3), (2 3 1) and (3 2 1) are considered to be equal
;
;  output:
;    max-move returns a list, the first element being win/loss status, and the
;    second being the next optimal state.
;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (max-move state)
  (define bestVal -8)
  (define bestMove '())
  (define children (successors state))
  (define S '())
  (define val -2)
  (if (listeq? state '())
      (list 'LOSS '())
      (begin 
        (if (or (listeq? state '(1)) (listeq? state '(2))) ; terminal state?
            (list 'WIN '())
            (begin 
              (let loop ((i 0))
                (set! S (list-ref children i))
                (set! val (min-value S))
                (if (> val bestVal)
                    (begin (set! bestVal val)
                           (set! bestMove S)))
                (if (< i (- (length children) 1))
                    (loop (+ i 1))))
              (cond ((eq? bestVal 1) (list 'WIN bestMove))
                    ((eq? bestVal -1) (list 'LOSS bestMove))))))))

(define (max-value state)
  (define best -8)
  (define val -8)
  (define children '())
  (define curState '())
  (if (or (listeq? state '(1)) (listeq? state '(2))) ; terminal state?
      (car (list 1))
      (begin
        (set! children (successors state))
        (let loop ((i 0))
          (set! curState (list-ref children i))
          (set! val (min-value curState))
          (if (> val best)
              (set! best val))
          (if (< i (- (length children) 1))
              (loop (+ i 1))))
        (car (list best)))))

(define (min-value state)
  (define best 8)
  (define val 8)
  (define children '())
  (define curState '())
  (if (or (listeq? state '(1)) (listeq? state '(2))) ; terminal state?
      (car (list -1))
      (begin
        (set! children (successors state))
        (let loop ((i 0))
          (set! curState (list-ref children i))
          (set! val (max-value curState))
          (if (< val best)
              (set! best val))
          (if (< i (- (length children) 1))
              (loop (+ i 1))))
        (car (list best)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; BEGIN SUCCESSOR FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; takes a state and returns a list of states with no duplicate elements
(define (successors state)
  (define childStates '()) ;list of children states
  (define val 0)
  (define n (- (length state) 1)) ;zero-based state length
  (if (= n -1)
      (list '()) ;if no states in list, no children
      (begin
        (let loop ((i 0))
          ; val = number of pins in next group
          (set! val (list-ref state i))
          (cond ((= val 1) ; 1 pin, knock it down
                 (set! childStates (append childStates
                                           (list (del val state)))))
                ((= val 2) ; 2 pins, knock down both or one
                 (set! childStates (append childStates
                                           (list (del val state))))
                 (set! childStates (append childStates
                                           (list (append (del val state) '(1))))))
                ((> val 2)
                 ; knock down one pin at a time
                 (set! childStates (append childStates (minusOne val state)))
                 ; knock down pairs of two pins
                 (set! childStates (append childStates (minusTwo val state)))))
          (if (< i n)
              (loop (+ i 1))))))
  
  (set! childStates (remove-duplicates (map binarysort childStates)))
  (car (list childStates)) ;return
  )

; take one pin at a time from positions 0 to ceiling((n/2)-1)
; returns a list of lists.. ex: ((1 2) (3 4) (5))
(define (minusOne val state)
  (define subStates '())
  (define n (ceiling (- (* val 0.5) 1))) ;set end index for loop
  (set! state (del val state)) ;new state (without val) used to build children
  (let loop ((i 0))
    (if (= i 0)
        (set! subStates (append subStates  ;end pins
                                (list (append state (list (- val 1))))))
        (set! subStates (append subStates  ;middle pins
                                (list (append state (list i (- (- val i) 1)))))))
    (if (< i n)
        (loop (+ i 1))))
  (car (list subStates))) ;return

; take two pins at a time from positions 0 to floor((n/2)-1)
; returns a list of lists.. ex: ((1 2) (3 4) (5))
(define (minusTwo val state)
  (define subStates '())
  (define n (floor (- (* val 0.5) 1))) ;set end index for loop
  (set! state (del val state)) ;new state (without val) used to build children
  (let loop ((i 0))
    (if (= i 0)
        (set! subStates (append subStates  ;end pins
                                (list (append state (list (- val 2))))))
        (set! subStates (append subStates  ;middle pins
                                (list (append state (list i (- (- val i) 2)))))))
    (if (< i n)
        (loop (+ i 1))))
  (car (list subStates))) ;return

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; END SUCCESSOR FUNCTIONS
;
; BEGIN BINARY SORT FUNCTIONS
; description:
;   We are going to represent each node in a binary tree as:
;   (value (left child)(right child))  If the node has no left 
;   and/or right child, this is represented with ().  So,
;   (2 (1()())(3()()))  is the tree:   2
;                                    /   \
;                                   1     3
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (binarysort L)
  (if (null? L) '()
      (traverse (btree L))))

(define (btree L)
  (if (= (length L) 1) (leaf (car L))
      (binsert (btree (cdr L)) (car L))))

; insert A into the tree
(define (binsert T A)
  (cond  ( (null? T) (leaf A) )
         ; left subtree
         ( (> (car T) A) (list (car T) 
                               (binsert (car (cdr T)) A)
                               (car (cdr (cdr T)))))
         ; right subtree
         ( else (list (car T)
                      (car (cdr T)) 
                      (binsert (car (cdr (cdr T))) A)))))

; add a leaf to the tree (A ()())
(define (leaf A)
  (list A '() '()))

; output sorted list by traversing the tree 
(define (traverse L)
  (cond ( (null? L) L)
        ( else
          (append (traverse (car (cdr L)))
                  (cons (car L)(traverse (car (cdr (cdr L)))))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; END BINARY SORT FUNCTIONS
;
; BEGIN UTIL FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; keeps first instance of each element in list, removes duplicates
(define (remove-duplicates list)
  (do ((list list (cdr list))
       (res  '()  (if (memq? (car list) res)
                      res
                      (cons (car list) res))))
    ((null? list)
     res)))

; returns #t if x is a member of l
(define (memq? x l)
  (let loop ((l l))
    (cond ((null? l)       #f)
          ((listeq? x (car l)) #t)
          (else            (loop (cdr l))))))

; removes the first instance of element from list
(define (del element list)
  (cond ((null? list) list)
        ((equal? element (car list)) (cdr list))
        (else (cons (car list) (del element (cdr list))))))


; compares two lists, returns #t if they are identical lists. identical
; lists must have the same length, same elements in the same order.
; author: Gabriel Smith (ges7506)
(define (listeq? l1 l2)
  (cond ((not (eq? (length l1) (length l2))) #f)
        (else 
         (let loop ((l1 l1)(l2 l2))
           (cond ((and (null? l1) (null? l2)) #t)
                 ((not (eq? (car l1) (car l2))) #f)
                 (else (loop (cdr l1) (cdr l2))))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; END UTIL FUNCTIONS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;