collinear(X,_,X,_,X,_):-!.
collinear(_,Y,_,Y,_,Y):-!.
collinear(X1,Y1,X2,Y2,X3,Y3):-
    X1=\=X2, X2=\=X3, X1=\=X3,
    Y1=\=Y2, Y2=\=Y3, Y1=\=Y3,
    P1 is (Y2-Y1)/(X2-X1),
    P2 is (Y3-Y2)/(X3-X2),
    P1=:=P2.

subsets([],[]).
subsets([[A,B], [A1,B1], [A2,B2]|T], [[[A,B], [A1,B1], [A2,B2]]|R]):-
    collinear(A,B,A1,B1,A2,B2),!,
    subsets([[A,B],[A2,B2]|T],R).
subsets([_|T],R):-subsets(T,R).

allsolutions(L, R):-findall(RP, subsets(L, RP), R).
