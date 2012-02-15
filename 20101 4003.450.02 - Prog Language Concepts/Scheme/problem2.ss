;; The first three lines of this file were inserted by DrScheme. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problem2) (read-case-sensitive #t) (teachpacks ((lib "testing.ss" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "testing.ss" "teachpack" "htdp")))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;
; $Id: problem2.txt,v 1.1 2009/03/29 22:21:51 bks Exp $
;
; $Author: bks $
;
; description: 
; Some "Numbers Games" and "lists of structures"
;
; problem assignment:
;     write the body for each unimplemented data structure and function.
;     write tests for each function at the end of the file.
;     complete and replace the 'todo' outputs 
;     you see when you run this program.
;
; restrictions: you must use only the basic operations of scheme,
;     which includes cond, cons, car and cdr.
;     you may not use length, apply, map, or any other higher level functions.
;
; you are encouraged to write helper functions as appropriate.
; Be sure you document them clearly and consistently.
;
; here is how you execute these exercises with Dr Scheme:
;   start drscheme
;   select language:
;     How To Design Programs intermediate student with lambda dialect and
;       testing.ss teachpack, which includes the check-expect function.
;     open the file problem2.ss
;     tell drscheme to run
;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


; 0 ;   this function is given to you.
; eqnums : num num -> true or false
; eqnums consumes 2 numbers.
; eqnums produces a boolean result. 

(define (eqnums anum1 anum2) 
  (= anum1 anum2))

; 1
; listmax : listo-num -> maximum value of input list
; listmax consumes a list of numbers.
; listmax produces the maximum value of the list.

(define (listmax alist)  
  (cond ((null? (cdr alist)) (car alist))
        (else
         (let ((x (listmax (cdr alist))))
         (cond ((> (car alist) x) (car alist))
               (else x))))))

; 2
; lists-add : listo-num listo-num -> listo-num
; lists-add consumes 2 lists of numbers.
; lists-add produces 1 list of numbers representing the
; pairwise sum of the input lists.
; NOTE: if the lengths of the lists are different, the result is false.
; #note: you cannot use the built-in length function#

(define (lists-add alist1 alist2) 
  (cond ((not (eq? (mylength alist1) (mylength alist2))) #f)
        (else
         (cond ((null? (cdr alist1)) (list (+ (car alist1) (car alist2))))
               (else (cons (+ (car alist1) (car alist2)) (lists-add (cdr alist1) (cdr alist2))))))))

(define (mylength l)
  (cond ((null? (cdr l)) 1)
        (else (+ 1 (length (cdr l))))))

; 3
; an item is a structure:
;    (make-item id price)
; where id is a symbol and price is a number.
; an item is an inventory record of something kept in a store inventory.

(define-struct item (id price))

; 4
; contains-item? : id inventory -> boolean
; contains-item? consumes a symbol for an item's id and an inventory.
; contains-item? produces true if the item-id is in the inventory and
; false if it is not found.

(define (contains-item? id inv)
  (cond ((empty? inv) #f)
        (else
         (cond ((symbol=? id (item-id (car inv))) #t)
               (else (contains-item? id (cdr inv)))))))

; 5
; cheapest-item : inventory -> item
; cheapest-item consumes an inventory.
; cheapest-item produces the item that has the lowest price.
; #note: the item is a structure#

(define (cheapest-item inv) 
  (cond ((null? (cdr inv)) (car inv))
        (else
         (let ((x (cheapest-item (cdr inv))))
           (cond ((< (item-price x) (item-price (car inv))) x)
                 (else (car inv)))))))


; 6
; rename-item : old-id new-id inventory -> inventory
; rename-item consumes an old-id symbol, a new-id symbol, and an inventory.
; rename-item produces a new, revised inventory with the old-id replaced
; by the new-id in every item whose id matches the old-id symbol.
; #note: there may be more than one item with the same id.#

(define (rename-item old-id new-id inv) 
  'unimplemented )


; 7
; show-inventory : inventory -> output
; show-inventory consumes an inventory list.
; show-inventory produces output showing the inventory's elements.
; the output format is shown in the this example:
; (show-inventory (list (make-item doodad 3.98))) # a 1 item inventory
; produces a single output line:
; Item: doodad $3.98
; The text "Item: " and " $" serve as standard labels for the item's
; field values.
; note: scheme's string functions will be needed here to assemble the
; data into an appropriate result for each item of inventory.

(define (show-inventory inv)
  (cond ((null? (cdr inv)) (showItem (car inv)))
        (else
         (string-append (showItem (car inv)) (show-inventory (cdr inv))))))

(define (showItem item)
  (string-append "Item: " (symbol->string (item-id item)) " $" (number->string (item-price item)) "\n"))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

"Here are the test results:"

"eqnums"
(check-expect (eqnums 3 2) false)
(check-expect (eqnums 2 2) true)

" todo: add more tests for each of the 'list' functions ..."

"listmax"
(check-expect (listmax '(-2)) -2)
(check-expect (listmax '(-2 1)) 1)

"lists-add"
(check-expect (lists-add '(3 2) '(2 3)) '(5 5))


"inventory structures and functions"
" todo: add tests for each of the item inventory functions ..."

;" todo: create several item instances"
(define hat (make-item 'hat 10))
(define gloves (make-item 'gloves 10))
(define boots (make-item 'boots 20))
(define coat (make-item 'coat 25))
(define shirt (make-item 'shirt 15))
(define pants (make-item 'pants 25))
(define scarf (make-item 'scarf 5))
(define socks (make-item 'socks 6))

;" todo: create several inventory instances containing 0, 1 and several items"
(define invMT '())
(define invHat (cons hat '()))
(define invAll (cons hat (cons gloves (cons boots (cons coat (cons shirt 
                (cons pants (cons scarf (cons socks '())))))))))
(define invTop (cons hat (cons coat (cons shirt (cons scarf '())))))
(define invBot (cons boots (cons pants (cons socks '()))))

;" todo: create executable test cases to test your contains-item? function"
(check-expect (contains-item? 'hat invMT) #f)
(check-expect (contains-item? 'hat invBot) #f)
(check-expect (contains-item? 'hat invAll) #t)
(check-expect (contains-item? 'hat invHat) #t)

;" todo: create executable test cases to test your cheapest-item function"
(check-expect (cheapest-item invTop) (make-item 'scarf 5))
(check-expect (cheapest-item invBot) (make-item 'socks 6))


;" todo: use your show-inventory function in testing"
(show-inventory invAll)