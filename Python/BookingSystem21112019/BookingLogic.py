class Booking_Element:
    def __init__(self, name='', car='', time='', date=''):
        self.name = name
        self.car = car
        self.time = time
        self.date = date
        self.id = 'id_number'

    def __str__(self):
        return f'id = {self.id}, name = {self.name}, car = {self.car}, time = {self.time}, dato = {self.date}'

    def get_name(self):
        return self.name

    def set_name(self, name):
        self.name = name

    def get_car(self):
        return self.car

    def set_car(self, car):
        self.car = car

    def get_time(self):
        return self.time

    def set_time(self, time):
        self.time = time

    def get_date(self):
        return self.date

    def set_date(self, date):
        self.date = date

    def get_id(self):
        return self.id

    def set_id(self, id_number):
        self.id = id_number


class Booking_Database:
    def __init__(self):
        self.database = []  # Laver en liste til bookings

    def get_new_id(self):  # Laver et id
        if len(self.database) == 0: # Første id er altid 0
            return 0
        else:
            return self.database[-1].get_id() + 1 # Lægger et tal til det seneste id

    def check_booking(self, time, date):  # Er der en booking med denne tid og dato?
        self.checker = 'not_occupied'

        for be in self.database:
            if be.get_time() == time and be.get_date() == date:
                self.checker = 'occupied'
            else:
                pass
        return self.checker

    def get_booking(self, id_number): # Returnerer en booking med specifikt id
        for be in self.database:
            if be.get_id() is id_number:
                return be
        return False

    def new_booking(self, name, car, time, date): # Oprætter ny booking
        if self.check_booking(time, date) == 'occupied': # Tjekker om tiden og datoen er optaget
            return "Tid optaget"
        else: # Hvis tid ikke er optaget, så opret en booking
            be = Booking_Element(name, car, time, date)
            be.set_id(self.get_new_id())
            self.database.append(be)
            return be

    def change_booking(self, id_number, new_name, new_car, new_time, new_date): # Ændrer en eksisterende booking
        be = self.get_booking(id_number)
        if be is not False:
            be.set_name(new_name)
            be.set_car(new_car)
            be.set_time(new_time)
            be.set_date(new_date)
            return be
        else:
            return 'fail'

    def delete_booking(self, id_number): # Sletter en booking med et specifikt id
        for be in self.database:
            if be.get_id() == id_number:
                self.database.remove(be)

    def list_bookings(self): # Returnerer en liste af alle bookings
        e = [] # Laver en ny liste så den originale liste (database) ikke bliver påvirket
        for be in self.database:
            e.append(str(be))
        return e

    def specific_list_of_bookings(self, start_date, end_date): # Returnerer en liste af bookings inden for to datoer
        e = []
        for be in self.database:
            if be.get_date() > start_date and be.get_date() < end_date:
                e.append(str(be))
        return e


def test(): # Midlertidig test

    bdb = Booking_Database()
    bdb.new_booking('kim', 'smart', '12', 20010908)
    print(bdb.list_bookings())
    bdb.new_booking('yes', 'smart', '12', 20010908)
    print(bdb.list_bookings())

    bdb.new_booking('1', '2', '3', 19890104)
    print(bdb.list_bookings())

    bdb.new_booking('mikkel', 'red bull', '13', 21440324)
    print(bdb.list_bookings())

    print(bdb.get_booking(2))
    bdb.change_booking(1,'bloed', 'forceindia', '20',23170930)
    print(bdb.list_bookings())

    print(bdb.specific_list_of_bookings(19000101, 21000101))

    bdb.delete_booking(0)
    print(bdb.list_bookings())



#test()
