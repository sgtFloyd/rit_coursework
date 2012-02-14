--update the stock of an item by 5
CREATE OR REPLACE PROCEDURE stock_increase(pID INT)
	UPDATE Product SET amount_in_stock = amount_in_stock + 5 WHERE id = pID;

--decrease the stock of an item. occurs when a user buys something.
CREATE OR REPLACE PROCEDURE stock_decrease(pID INT)
	UPDATE Product SET amount_in_stock = amount_in_stock - 1 WHERE id = pID;

--this trigger simply prevents the stock from running out.
-- adds 5 to the stock whenever an item sells out.
--CREATE OR REPLACE TRIGGER purchase_trigger
--    BEFORE UPDATE OF amount_in_stock ON Product
--    FOR EACH ROW
--   	BEGIN
--    	IF (:new.amount_in_stock <=0) THEN
--    		call stock_increase(n.id); 
--    	END IF;
--    END;
--.
--RUN;


CREATE OR REPLACE TRIGGER voter_trg
AFTER INSERT OR UPDATE OF dob ON Voter
FOR EACH ROW 
BEGIN 
    IF ((:new.dob+18*365) < sysdate) THEN 
        RAISE_APPLICATION_ERROR(-12345, 'Cannot be a voter'); 
    END IF; 
END; 
. 
RUN; 
