balance([], 0).
balance([H|T], C) :-
    H == '(',
    NC is C + 1,
    balance(T, NC).
balance([H|T], C) :-
    H == ')',
    C > 0,
    NC is C - 1,
    balance(T, NC).

par('(').
par(')').

generate(N, N, C, C):-!.
generate(N, I, C, R):-
    par(X),
    NI is I + 1,
    generate(N, NI, [X|C], R).

back(N, R):-
    generate(N, 0, [], R),
    balance(R, 0).

all(N, R):-
    findall(RPart, back(N, RPart), R).
