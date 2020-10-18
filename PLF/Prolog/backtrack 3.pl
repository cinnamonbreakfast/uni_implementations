onesolution(0, _, []).
onesolution(N, E, [E|R]):-
    N >= E,
    NN is N - E,
    NE is E + 1,
    onesolution(NN, NE, R).

decomposition(N, E, R):-
    E < N, onesolution(N, E, R).
decomposition(N, E, R):-
    E < N, NE is E + 1,
    onesolution(N, NE, R).


allsolutions(N, R):-
    findall(RP, decomposition(N, 1, RP), R).
