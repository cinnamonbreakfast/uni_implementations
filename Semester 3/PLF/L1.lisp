LISP 1

;------ 1
;--------------------------------------------------- a
; turn a linear list into a set
(defun gett(l k i)
    (cond
        ((null l) nil)
        ((= k i) (car l))
        (T (gett (cdr l) k (+ 1 i)))
    )
)

(print (gett '(1 2 3 4 5 6) 8 1))


;--------------------------------------------------- b
; check if exists (non linear)
(defun contains(l e)
    (cond
        ((null l) nil)
        ((listp (car l)) (OR (contains (car l) e) (contains (cdr l) e)))
        ((= e (car l)) T)
        (T (contains (cdr l) e))
    )
)

(print (contains '(1 2 ((3) 4) 5 6) 6))

;--------------------------------------------------- c
; count the subsets (including the initial one)
(defun fnc(l)
    (cond
        ((null l) 0)
        ((listp (car l)) (+ 1 (fnc (car l)) (fnc (cdr l))))
        (T (fnc (cdr l)))
    )
)

(defun sublist(l) (+ (fnc l) 1)) ; initial list

(print (sublist '(1 2 (3 (4 5) (6 7)) 8 (9 10))))

;--------------------------------------------------- d
; turn a linear list into a set

(defun contains (l e)
    (cond
        ((null l) nil)
        ((= (car l) e) T)
        (T (contains (cdr l) e))
    )
)

(defun makeset(l)
    (cond
        ((null l) nil)
        ((contains (cdr l) (car l)) (makeset (cdr l)))
        (T (cons (car l) (makeset (cdr l))))
    )
)

(print (makeset '(1 2 3 2 3 4 1 1)))


;------ 2
;--------------------------------------------------- a
; dot product (1 2 3) * (4 5 6) = 1*4 + 2*5 + 3*6 
; (aici la flex faci apply si mapcar si d astea...)

(defun dotp(a b)
    (cond
        ((null a) 0) ; same dimension assumption ffs
        (T (+ (* (car a) (car b)) (dotp (cdr a) (cdr b))))
    )
)

(print (dotp '(1 2 3) '(4 5 6)))



;--------------------------------------------------- b
; depth of a non-linear list

(defun lookup(l)
    (cond
        ((null l) 0)
        ((listp (car l)) (+ (lookup (car l)) 1 (lookup (cdr l))))
        (T (lookup (cdr l)))
    )
)

(defun depth(l)
    (+ 1 (lookup l))
)

(print (depth '(2 (2 ((4)) 2) 2)))

;--------------------------------------------------- c
; make a sorted set

;; this program needs to be fixed (works on particular cases)
(defun combine (l k)
    (if (null l) 
        k
        (cons (car l) (combine (cdr l) k))
    )
)

(defun insort(l e)
    (cond
        ((null l) (list e))
        ((> e (car l)) (combine (list (car l)) (insort (cdr l) e)))
        (T (cons e l))
    )
)

(defun contains (l e)
    (cond
        ((null l) nil)
        ((= (car l) e) T)
        (T (contains (cdr l) e))
    )
)

(defun main(l)
    (cond
        ((null l) nil)
        ((contains (cdr l) (car l)) (main (cdr l)))
        (T (insort (cdr l) (car l)))
    )
)

;(print (insort '(1 2 4 5) 0))
(print (main '(3 1 2 2 3 4 1)))

;--------------------------------------------------- d
; intersection of two sets - dracu sa ma ia, not sorted

(defun contains (l e)
    (cond
        ((null l) nil)
        ((= (car l) e) T)
        (T (contains (cdr l) e))
    )
)

(defun intersect(l r)
    (cond
        ((null r) l)
        ((contains l (car r)) (intersect l (cdr r)))
        (T (intersect (cons (car r) l) (cdr r)))
    )
)

(print (intersect '(1 2 3 4) '(4 5 6 7)))


;------ 3
;--------------------------------------------------- a
; insert after even pos (2nd, 4th...)

(defun addtom(l a p)
    (cond
        ((null l) nil)
        ((= (mod p 2) 0) (cons (car l) (cons a (addtom (cdr l) a (+ p 1)))))
        (T (cons (car l) (addtom (cdr l) a (+ p 1))))
    )
)

(print (addtom '(1 2 3 4 5) 'a 1))

;--------------------------------------------------- b
; get all the atoms in rev order
; (((A B) C) (D E)) ==> (E D C B A)

(defun combine (l k)
    (if (null l) 
        k
        (cons (car l) (combine (cdr l) k))
    )
)

(defun combo(l)
    (cond
        ((null l) nil)
        ((listp (car l)) (combine (combo (car l)) (combo (cdr l))))
        (T (cons (car l) (combo (cdr l))))
    )
)

(defun mccombo(l)
    (reverse (combo l))
)

(print (mccombo '(((A B) C) (D E))))

;--------------------------------------------------- c
; greatest common divisor in a list

(defun common(l p)
    (cond
        ((null (cdr l)) (car l))
        (T (gcd (car l) (common (cdr l) p)))
    )
)

(print (common '(1 2 4 6 8) 1))

;--------------------------------------------------- d
; number of occurences of an atom (use eq, not =)
(defun occur(l a)
    (cond
        ((null l) 0)
        ((eq (car l) a) (+ 1 (occur (cdr l) a)))
        (T (occur (cdr l) a))
    )
)

(print (occur '(a 2 3 4 a 2 3 2 a) 'a))


;------ 4
;--------------------------------------------------- a
; sum of two vectors (geometric pov)

(print (mapcar #'+ '(1 2 3) '(4 5 6)))

;--------------------------------------------------- b
; non linear to linear, keep order

(defun combine (l k)
    (if (null l) 
        k
        (cons (car l) (combine (cdr l) k))
    )
)

(defun combo(l)
    (cond
        ((null l) nil)
        ((listp (car l)) (combine (combo (car l)) (combo (cdr l))))
        (T (cons (car l) (combo (cdr l))))
    )
)

(print (combo '(((A B) C) (D E))))

;--------------------------------------------------- c
; i quit

;--------------------------------------------------- d
; max number on lvl 1 (!doesnt work on negatives)

(defun maxi(l)
    (cond
        ((null l) 0)
        ((numberp (car l)) (max (car l) (maxi (cdr l))))
        (T (maxi (cdr l)))
    )
)


(print (maxi '(1 2 3 a 4 (5 6) b))) ; => 4

;------ 5
;--------------------------------------------------- a
; write nth pos twice for academic purposes

(defun twice(l i p)
    (cond
        ((null l) nil)
        ((= p i) (cons (car l) l))
        (T (cons (car l) (twice (cdr l) (+ i 1) p)))
    )
)

(print (twice '(1 2 3 4 5) 1 3))

;--------------------------------------------------- b
;  (A B C) (X Y Z) --> ((A.X) (B.Y) (C.Z))
(print (mapcar #'cons '(A B C) '(X Y Z)))


;--------------------------------------------------- c
; nr of sublists (including the list itself)
(defun lookup(l)
    (cond
        ((null l) 0)
        ((listp (car l)) (+ (lookup (car l)) 1 (lookup (cdr l))))
        (T (lookup (cdr l)))
    )
)

(defun depth(l)
    (+ 1 (lookup l))
)

(print (depth '(2 (2 ((4)) 2) 2)))

;--------------------------------------------------- d
; nr of numbers on lvl1 (i took maxi from above and changed literally one character)

(defun maxi(l)
    (cond
        ((null l) 0)
        ((numberp (car l)) (+ 1 (maxi (cdr l))))
        (T (maxi (cdr l)))
    )
)


(print (maxi '(1 2 3 a (5 6) b)))


;------ 6
;--------------------------------------------------- a
; check if list is lin

(defun islin(l)
    (cond
        ((null l) t)
        ((listp (car l)) nil)
        (T (islin (cdr l)))
    )
)


(print (islin '(1 (2) 3 4)))

;--------------------------------------------------- b
; replace occurences of e with o
; EEEEEEEeeeeeeeeeeeee!!
; OOOOOOOooooooooooooooo!!

(defun rpl(l e o)
    (cond
        ((null l) nil)
        ((eq (car l) e) (cons o (rpl (cdr l) e o)))
        (T (cons (car l) (rpl (cdr l) e o)))
    )
)

(print (rpl '(1 2 3 1 2 3 1 1) 1 9))

;--------------------------------------------------- c
; 