import unittest
from karatsuba import karatsuba, splitInTwo, appendZeros, addStringsAsInt

class MyTestCase(unittest.TestCase):
    def test_split(self):
        firsthalf, secondhalf = splitInTwo('1234')
        self.assertEqual(firsthalf, '12')
        self.assertEqual(secondhalf, '34')

        firsthalf, secondhalf = splitInTwo('123499923')
        self.assertEqual(firsthalf, '1234')
        self.assertEqual(secondhalf, '99923')

    def test_km(self):
        result = karatsuba(56, 12)
        self.assertEqual(result, 672)

    def test_appendZeros(self):
        result = appendZeros('123', 3)
        self.assertEqual(result, '123000')

    def test_result(self):
        result = karatsuba(3141592653589793238462643383279502884197169399375105820974944592,
                           2718281828459045235360287471352662497757247093699959574966967627)
        print(result)
        self.assertEqual(result, 8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184)
        print(result)

if __name__ == '__main__':
    unittest.main()
