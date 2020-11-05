USE Practic
GO

INSERT INTO Restaurant VALUES ('Piata Muzeului', 'Caro');
INSERT INTO Restaurant VALUES ('Piata Muzeului', 'Marty');
INSERT INTO Restaurant VALUES ('Piata Unirii', 'Rhody');
INSERT INTO Restaurant VALUES ('Piata Unirii', 'L`alchemist');
INSERT INTO Restaurant VALUES ('Piata Unirii', 'Che Guevara');
INSERT INTO Restaurant VALUES ('Avram Iancu', 'Casa TIFF');

INSERT INTO Menu VALUES ('Drinks', 1);
INSERT INTO Menu VALUES ('Food', 1);
INSERT INTO Menu VALUES ('Drinks', 6);
INSERT INTO Menu VALUES ('Food', 6);
INSERT INTO Menu VALUES ('Food', 2);

INSERT INTO Dish VALUES ('Jagermeister 50ml', 10, 1);
INSERT INTO Dish VALUES ('Coca-Cola 330ml', 7, 1);
INSERT INTO Dish VALUES ('White wine 750ml', 65, 1);

INSERT INTO Dish VALUES ('Fries with cheese', 15, 2);
INSERT INTO Dish VALUES ('Nachos', 15, 2);
INSERT INTO Dish VALUES ('Pizza 1', 35, 2);
INSERT INTO Dish VALUES ('Pizza 2', 35, 2);

INSERT INTO Dish VALUES ('Irish coffee', 15, 3);
INSERT INTO Dish VALUES ('Flavoured latte', 15, 3);

INSERT INTO Dish VALUES ('Burger', 25, 4);
INSERT INTO Dish VALUES ('Pancakes', 15, 4);


INSERT INTO Client VALUES('Joe');
INSERT INTO Client VALUES('Ana');
INSERT INTO Client VALUES('Ion');

INSERT INTO Eat VALUES(1, 1, GETDATE(), '01:00:00')
INSERT INTO Eat VALUES(2, 2, GETDATE(), '01:00:00')