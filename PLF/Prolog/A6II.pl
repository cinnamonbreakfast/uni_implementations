insert([], E, [E]).
insert([H|T], E, [E,H|T]).
insert([H|T], E, [H|R]):-insert(T,E,R).

perm([],[]).
perm([H|T], R):-perm(T, RP),
insert(RP, H, R).

abs(N, N):-N>=0.
abs(N, -N).

check([_]).
check([H1, H2|T]):-
    P is abs(H2-H1),
    P =< 3,
    check([H2|T]).

onesol(L, R):-
    perm(L, R),
    check(R).

allsol(L, R):-
    findall(RT, onesol(L, RT), R).

sublists([],[]).
sublists([H|T], [H|R]):-sublists(T,R).
sublists([_|T], R):-sublists(T,R).

chec([_]).
chec([H1, H2|T]):-H1<H2, check([H2|T]).

onesole(L, R):-sublists(L, R), chec(R).

alls(L, R):-findall(RT, onesole(L, RT), R).

