sublists([], []).
sublists([H|T], [H|R]):-sublists(T,R).
sublists([_|T], R):-sublists(T, R).

sum([], 0).
sum([R], R).
sum([H|T], R):-sum(T, R1), R is R1 + H, !.

div(X):-R is X mod 3, R =:= 0.

count([], 0).
count([_], 1).
count([_|T], R):-count(T, R1), R is R1+1, !.

check(L, N):-count(L, R), R >= N, sum(L, S), !, div(S).

onesol(L, N, R):-sublists(L, R), check(R, N).

allsol(L, N, R):-findall(RT, onesol(L, N, RT), R).

allsub(L, R):-findall(RT, sublists(L, RT), R).
