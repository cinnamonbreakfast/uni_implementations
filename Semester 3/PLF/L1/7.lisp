;gnu clisp 2.49

;; ---------------------------------------------------------------------------------------- A

;; remove the nth element

(defun remN (l n)
    (cond
        ((null l) nil)
        ((= n 1) (cdr l))
        (T (cons (car l) (remN (cdr l) (- n 1))))
    )
)

(print (remN '(1 2 3 4 5 6 7 8) 5))

;; ---------------------------------------------------------------------------------------- B

;; add with carry

(defun adc(l c)
    (cond
        ((null l) nil)
        ((> (+ (car l) c) 9) (cons 0 (adc (cdr l) 1)))
        (T (cons (+ (car l) c) (adc (cdr l) 0)))
    )
)

(print (reverse (adc (reverse '(1 2 9 8)) 1)))

;; ---------------------------------------------------------------------------------------- C

;; atoms of a non-linear list

(defun my_append (l k)
    (if (null l) 
        k
        (cons (car l) (my_append (cdr l) k))
    )
)

(defun linear (l)
    (cond
        ((null l) nil)
        ((numberp (car l)) (cons (car l) (linear (cdr l))))
        ((listp (car l)) (my_append (linear (car l)) (linear (cdr l))))
        (T (linear (cdr l)))
    )
)


(defun contains (l e)
    (cond
        ((null l) nil)
        ((= (car l) e) T)
        (T (contains (cdr l) e))
    )
)

(defun clear (l e)
    (cond
        ((null l) nil)
        ((= (car l) e) (clear (cdr l) e))
        (T (cons (car l) (clear (cdr l) e)))
    )
)

(defun fil (l)
    (cond
        ((null l) nil)
        ((null (cdr l)) l)
        ((contains (cdr l) (car l)) (cons (car l) (fil (clear (cdr l) (car l)))))
        (T (cons (car l) (fil (cdr l))))
    )
)

;(print (clear '(1 2 3 4 1 2) 1))
(print (fil (linear '(1 (2 (1 3 (2 4) 3) 1) (1 4)))))

;; ---------------------------------------------------------------------------------------- D

(defun contains (l e)
    (cond
        ((null l) nil)
        ((= (car l) e) T)
        (T (contains (cdr l) e))
    )
)

(defun isSet (l)
    (cond
        ((null l) T)
        ((contains (cdr l) (car l)) nil)
        (T (isSet (cdr l)))
    )
)

(print (isSet '(1 2 3 4)))
;=> T

(print (isSet '(1 2 2 3 4)))
;=> nil
