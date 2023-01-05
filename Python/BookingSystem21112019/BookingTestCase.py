import BookingSystem
import unittest



class Test_New_Booking(unittest.TestCase):
	def test_new_booking(self): # Tester ny booking
		bdb = BookingSystem.Booking_Database() # Laver lokal variabel af programmet

		liste_1 = bdb.list_bookings()
		bdb.new_booking('Karl', 'Toyota', '10:30', 20010809)
		liste_2 = bdb.list_bookings()
		self.assertTrue(len(liste_2) - len(liste_1) == 1) # Er der kommet en ny booking i listen med bookings=
		self.assertTrue(bdb.get_booking(0).get_name() == 'Karl')
		self.assertTrue(bdb.get_booking(0).get_car() == 'Toyota')
		self.assertTrue(bdb.get_booking(0).get_time() == '10:30')
		self.assertTrue(bdb.get_booking(0).get_date() == 20010809)
		self.assertTrue(bdb.new_booking('Anders', 'BMW', '10:30', 20010809) == "Tid optaget") # Tiden burde være optaget

	def test_change_booking(self): # Tester en ændring i en booking
		bdb = BookingSystem.Booking_Database()

		bdb.new_booking('Jørgen', 'Audi', '11:45', 19951121)
		bdb.change_booking(0,'Hans', 'Porsche', '16:20', 19951122) # Ændrer bookingen med id'et 0 (den første)
		self.assertTrue(bdb.get_booking(0).get_name() == 'Hans')
		self.assertTrue(bdb.get_booking(0).get_car() == 'Porsche')
		self.assertTrue(bdb.get_booking(0).get_time() == '16:20')
		self.assertTrue(bdb.get_booking(0).get_date() == 19951122)

	def test_slet_booking(self): # Tester slettelse af booking
		bdb = BookingSystem.Booking_Database()

		bdb.new_booking('Thomas', 'Ford', '13:48', 20190102)
		bdb.delete_booking(0)
		self.assertTrue(len(bdb.list_bookings()) == 0) # Der burde være 0 bookings i listen af bookings

	def test_list_bookings(self): # Tester liste af alle bookings
		bdb = BookingSystem.Booking_Database()

		bdb.new_booking('test1', 'test1', 'test1', 12345678)
		self.assertTrue(len(bdb.list_bookings()) == 1)
		bdb.new_booking('test2', 'test2', 'test2', 22345678)
		self.assertTrue(len(bdb.list_bookings()) == 2)
		bdb.new_booking('test3', 'test3', 'test3', 32345678)
		self.assertTrue(len(bdb.list_bookings()) == 3) # Der burde være i alt 3 bookings til sidst

	def test_specific_list_of_bookings(self): # Tester liste af alle bookings i et tidsrum
		bdb = BookingSystem.Booking_Database()

		bdb.new_booking('test1', 'test1', 'test1', 19990101)
		bdb.new_booking('test2', 'test2', 'test2', 20000101)
		bdb.new_booking('test3', 'test3', 'test3', 20050101)
		bdb.new_booking('test4', 'test4', 'test4', 20190101)
		self.assertTrue(len(bdb.specific_list_of_bookings(19990102, 20100101)) == 2) # Her søges efter dato, der burde være 2 bookings mellem de valgte datoer

if __name__ == "__main__":
	unittest.main()
