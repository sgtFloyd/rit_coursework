;; The first three lines of this file were inserted by DrScheme. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname how-hot) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; file name: how-hot.ss
; your name: Gabriel Smith
; your login: ges7506
;
; description: 
;
; Write a Scheme function (how-hot pepper) that produces the Scoville scale
; range for the head of the input pepper symbol. For example (how-hot 'jalapeno)
; consumes the symbol jalapeno and produces the string "2,500-8,000"; the commas
; in the output are optional, but the quotes are part of the data type's output
; format. The function produces a double-quoted string whose format is
; "from-to", where "from" is the low value of the Scoville range, and "to" is
; the high bound for the subject pepper. You may use the Scoville scale from
; wikipedia, here: Scoville Scale [http://en.wikipedia.org/wiki/Scoville_scale]
; Be sure to write tests that run when DrScheme is told to run.

(define (how-hot p)
  (cond
    ((or (eq? p 'pimento) (eq? p 'pepperoncini) (eq? p 'bell)) '"100-500")
    ((or (eq? p 'poblano) (eq? p 'rocotillo)) '"500-2,500")
    ((eq? p 'anaheim) '"500-8,000")
    ((or (eq? p 'jalapeno) (eq? p 'guajillo)(eq? p 'paprika)) '"2,500-8,500")
    ((eq? p 'serrano) '"10,000-23,000")
    ((eq? p 'chipotle) '"10,000-50,000")
    ((or (eq? p 'cayenne) (eq? p 'aji) (eq? p 'tabasco)) '"30,000-50,000")
    ((or (eq? p 'thai) (eq? p 'malagueta)
         (eq? p 'chiltepin) (eq? p 'pequin)) '"50,000-100,000")
    ((or (eq? p 'habanero\ chili) (eq? p 'scotch\ bonnet) (eq? p 'datil)
         (eq? p 'rocoto) (eq? p 'jamacian\ hot) (eq? p 'african\ birdseye)
         (eq? p 'madame\ jeanette)) '"100,000-350,000")
    ((eq? p 'red\ savina) '"350,000-580,000")
    ((eq? p 'naga\ jolokia) '"855,000-1,050,000")
    ((or (eq? p 'pepper\ spray) (eq? p 'FN\ 303)) '"500,000-5,300,000")
    ((eq? p 'capsaicinoids) '"8,600,000-9,100,000")
    ((eq? p 'capsaicin) '"15,000,000-16,000,000")
    (else '"Invalid Pepper Name")
    ))

; TESTS
(how-hot 'jalapeno) ;should be "2,500-8,500"
(how-hot 'rocoto) ;should be "100,000-350,000"
(how-hot 'bogus) ;should be "Invalid Pepper Name"
(how-hot 'FN\ 303) ;should be "500,000-5,300,000"
(how-hot 'capsaicin) ;should be "15,000,000-16,000,000"