AMEMframework

TEST INSTRUCTIONS

-- Test 0 - Verify computer folder C:\AMEMLOGS 
-- Result: Should not exist

-- Test 1 - Verify connection
connectiontest
-- Result: ""Sucess!"
-- OBS: it will take some seconds until de connection pool creation

-- Test 2 - Verify connection data
connectioninfo
-- Result: "jdbc:oracle:thin@<IP>:<PORT>:<BASE>

-- Test 3 - Verify functions list
help
-- Result: Show operator and parameters

-- Test 4 - Anchor Creation
createAnchor(Container, CO, teste2, number(10), true, container)
## Must be confirmed ##
-- Result: "Object created!"

-- Teste 5 - Try to add existing object
createAnchor(Container, CO, teste2, number(10), true, container)
-- Result: "ORA-00955 ..."

-- Teste 6 - Attribute creation
createAttribute(teste2, CO_CONTAINER, Sigla, SIG, teste2, varchar(10), descricao, null, null, null)
## Must be confirmed ##
-- Result:

-- Teste 7 - Try to create knot with wrong capsule
createKnot(Colors, COS, teste3, varchar(15), number(5), true, knot)
-- Result:"Object br.com.ufabc.amem.model.am.Capsule@xxxxx from tipe class br.com.ufabc.amem.model.am.Capsule is invalid! Operation Canceled."

-- Teste 8 - Knot creation
createKnot(Colors, COS, teste2, varchar(15), number(5), true, knot)
## Must be confirmed ##
-- Result:"Object created!"

-- Teste 9 - Historrized Attribute creation
createAttribute(teste2, CO_CONTAINER, Color, COL, teste2, varchar(10), descricao, TIMESTAMP(3), teste2, COS_COLORS)
## Must be confirmed ##
-- Result:"Object created!"

-- Teste 10 - Historizze Attribute (Not implemented)
historizeAttribute(teste2, co_sig_container_sigla, teste2, timestamp(3), 24/07/1992)
## Must be confirmed ##
-- Result: "Invalid Parameter number for funcion historizeAttribute"

-- Test 13 - Not existing function
thisisnotafunction
-- Result: "This is not a valid function"

-- Test 12 - Verify computer folder C:\AMEMLOGS 
-- Result: Should have JSON operation logs C:\AMEMLOGS\<YEAR>\<MONTH>\<DAY>.log

-- Test 13 - end
exit
-- Result: close the system
