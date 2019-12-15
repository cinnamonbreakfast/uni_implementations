; --------------------------------------------- LEFT CHILD
(defun leftChild(l children)
	(COND
		((= children 0) NIL )
		(T
			(cons
				(car l) 
				(cons
					(cadr l)
					(leftChild
						(cddr  l)
						(+ (- children 1) (cadr l))
					)
				)
			)
		)
	)
)

; --------------------------------------------- LEFT CHILD
(defun rightChild(l children)
	(COND
		((= children 0) l )
		(T
			(rightChild
				(cddr l)
				(+ (- children 1) (cadr l))
			)
		)
	)
)

; --------------------------------------------- MAIN
(defun nodesOnLevel(l k level)
	(COND
		((null l) nil)
		((= level k) (list (car l)))
		((eq (cadr l) 0) nil)
		(T
			(append
				(nodesOnLevel
					(leftChild (cddr l) 1)
					k
					(+ 1 level)
				)
				(nodesOnLevel
					(rightChild
						(cddr l)
						1
					)
					k
					(+ 1 level)
				)
			)
		)
	)
)

(print (nodesOnLevel '(A 2 B 0 C 2 D 0 E 0) 2 0))
(print (nodesOnLevel '(A 2 B 2 D 0 E 2 H 0 I 0 C 2 F 0 G 0) 2 0))