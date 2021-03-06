comb([E|_], 1, [E]).
comb([_|L], N, R):-comb(L, N, R).
comb([A|L], N, [A|R]):-
    N =\= 1,
    N1 is N - 1,
    comb(L, N1, R).

insert([], E, [E]).
insert([H|T], E, [E, H|T]).
insert([H|T], E, [H|R]):-insert(T, E, R).

perm([], []).
perm([H|T], R):-perm(T, RP), insert(RP, H, R).

aran(L, K, R):-comb(L, K, RC), perm(RC, R).

arang(L, K, R):-findall(RT, aran(L, K, RT), R).

sublists([], []).
sublists([H|T], [H|R]):-sublists(T, R).
sublists([_|T], R):-sublists(T, R).

sum([], 0).
sum([E], E).
sum([H|T], R):-sum(T, R1), R is R1+H.

check(L, S):-sum(L, R), R=:=S.

onesol(L, S, R):-sublists(L, R), check(R, S).

allsol(L, S, R):-findall(RT, onesol(L, S, RT), R).
