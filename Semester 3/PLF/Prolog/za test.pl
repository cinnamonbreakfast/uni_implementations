divs(2, _, C):- C is 0, !.
divs(3, _, C):- C is 0, !.

divs(N, D, C):- N =:= D , C is 0 ,!.
divs(N, D, C):- N > 3, N div D =\= 0, divs(N, D+1, C), !.
divs(N, D, C):- N > 3, N div D =:= 0, divs(N, D+1, C1), C is C1+1, !.
