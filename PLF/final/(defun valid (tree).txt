(defun valid (tree)
    (and (numberp tree)
         (equal (mod tree 3) 0)
    )
)

(defun traverse (tree)
    (cond
        ((valid tree) nil)
        ((atom tree) (list tree))
        (t (list (mapcan 'traverse tree)))
    )
)

(print (traverse '(1 (2 A (3 A)) (6))))

(defun g(l) (mapcon 'list l))

(print (apply 'append (mapcon 'g '(1 2 3))))

(print (mapcon 'list '(1 2 3)))

L3 ---------------------------
--------p1

(defun exists(l e)
    (cond
        ((null l) 0)
        ((eq e l) 1)
        ((atom l) 0)
        (t (apply '+ (mapcar #'(lambda (x) (exists x e)) l)))
    )
)

(defun main(l e)
    (cond
        ((null l) nil)
        ((> (exists l e) 0) t)
    )
)

(print (main '(1 2 3 4 5 6) 9))
(print (exists '(1 2 3 4 5 6) 9))

---------p2

(defun exists(l)
    (cond
        ((null l) 0)
        ((numberp l) l)
        ((atom l) 0)
        (t (apply '+ (mapcar #'exists l)))
    )
)

(print (exists '(1 2 3 A B 4 5 6)))


---------p3

(defun exists(l e)
    (cond
        ((null l) 0)
        ((eq e l) 1)
        ((atom l) 0)
        (t (apply '+ (mapcar #'(lambda (x) (exists x e)) l)))
    )
)

(defun main(l e)
    (cond
        ((null l) nil)
        ((> (exists l e) 0) t)
    )
)

(print (main '(a (b (c g h)) (d) (E (f))) 'h))

---------p4

(defun exists(l)
    (cond
        ((null l) 1)
        ((numberp l) l)
        ((atom l) 1)
        (t (apply '* (mapcar #'exists l)))
    )
)

(print (exists '(1 2 3 A B 6)))

---------p5

(defun exists(l)
    (cond
        ((null l) 0)
        ((and (numberp l) (evenp l)) l)
        ((numberp l) (- 0 l))
        ((atom l) 0)
        (t (apply '+ (mapcar #'exists l)))
    )
)

(print (exists '(1 2 3 A (2 3) B 6)))

--------p6

(defun exists(l)
    (cond
        ((null l) 0)
        ((numberp l) l)
        ((atom l) 0)
        (t (apply 'max (mapcar #'exists l)))
    )
)

(print (exists '(1 2 3 A (2 3) B 6 2 4)))

--------p7

(defun exists(l e k)
    (cond
        ((null l) nil)
        ((eq l e) k)
        ((atom l) l)
        (t (apply 'list (mapcar #'(lambda (x) (exists x e k)) l)))
    )
)

(print (exists '(1 2 3 A (2 3) B 6 2 4) 3 '(9 9 9)))

; e un mare cacat, nu merge chiar ok

--------p8

(defun nrNodes(tree lvl)
	(cond
		((null tree) 0)
		((eq lvl 0) 1)
		(T
			(apply '+
				(mapcar #'(lambda(x) (nrNodes x (- lvl 1))) (cdr tree))
			)
		)
	)
)

(print (nrNodes '(a (b (c)) (d) (e (f))) 2))

-------p9

(defun filter(l e)
    (cond
        ((null l) nil)
        ((and (atom l) (eq l e)) '())
        ((atom l) (list l))
        (t (list (mapcan #'(lambda(x) (filter x e)) l)))
    )
)

(defun main(l e) (car (filter l e)))

(print (main '(1 2 (3 (3 5) 4) 3 4 (3)) 3))

; been on my exam, subject III

-------p10

(defun rep(tree l k)
    (cond
        ((null tree) nil)
        ((and (atom tree) (equal tree l)) k)
        ((atom tree) tree)
        (t (mapcar #'(lambda(x) (rep x l k)) tree))
    )
)

(print (rep '(a (b (c)) (d) (e (f))) 'b 'g))

--------p11

(defun depth(tree)
    (cond
        ((null tree) 0)
        ((atom tree) 0)
        (t (+ 1 (mapcan 'depth tree)))
    )
)

(print (depth '(a (b (c)) (d) (e (f)))))

--------p12

p7

--------p13

(defun depth(tree l)
    (cond
        ((or (< l 0) (null tree)) nil)
        ((and (atom tree) (eq l 0)) (list tree))
        ((atom tree) nil)
        (t (car tree) (apply 'append
                  (mapcar #'(lambda(x)
                            (depth x (- l 1))
                     )
                     tree
                )
            )
        )
    )
)

(print (depth '(a (b (c)) (d) (e (f))) 2))
;;; just the nodes without root, fml

-------p14

(defun rev(l)
    (cond
        ((null l) nil)
        ((or (atom l) (numberp l)) l)
        (t (append (rev (cdr l)) (list (car l))))
    )
)

(DEFUN my_reverse (L)
    (COND
        ((NULL L) NIL)
        ((ATOM L) L)
        (T (MAPCAR 'my_reverse (rev L)))
    )
)

(print (my_reverse '(((A B) C) (D E))))

--------p15

(defun linear(l)
    (cond
        ((null l) nil)
        ((atom l) (list l))
        (t (mapcan 'linear l))
    )
)

(print (linear '(((A B) C) (D E))))