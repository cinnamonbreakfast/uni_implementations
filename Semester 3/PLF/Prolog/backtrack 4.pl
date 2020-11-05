gensub([],[]).
gensub([H|T], [H|Rt]):-
  gensub(T, Rt).
gensub([_|T], Rt):-
  gensub(T, Rt).


check([_]).
check([H1,H2|T]):-H2>H1,
  check([H2|T]).

onesol(L, R1):-
  gensub(L, R1), check(R1).

allsol(L, R):-findall(RT, onesol(L, RT), R).
