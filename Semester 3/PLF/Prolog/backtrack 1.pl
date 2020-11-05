inserare([], E, [E]).
inserare([H|T], E, [E, H|T]).
inserare([H|T], E, [H|R]):-inserare(T, E, R).

combinari(_, 0, []).
combinari([H|T], K, [H|R]):-
    K > 0,
    NK is K - 1,
    combinari(T, NK, R).
combinari([_|T], K, R):- K > 0, combinari(T, K, R).

allsolutions(L, N, R):-findall(RPart, combinari(L, N, RPart), R).