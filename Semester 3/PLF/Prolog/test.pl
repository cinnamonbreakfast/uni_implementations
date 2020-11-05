repeat([], _, 0).
repeat([H|T], E, R):-E == H, repeat(T, E, R1), R is R1 + 1, !.
repeat([_|T], E, R):-repeat(T, E, R).



filter([H|T], [H|R]):-
    repeat([H|T], H, R1),
    R1 == 1,
    filter(T, R).

filter([_|T], R):-filter(T, R).
filter([], []).
