# pinboard-backend
Serwis ogłoszeniowy

#### Motywacja
Project created during courses Projektowanie Aplikacji Internetowych and Organizacja i Rozwj projektw Open Source.

#### Tech & framework
* Java 8
* Maven 3.3.9
* Spring Boot 2.2.7
* Hibernate 5.4.15
* PostgreSQL

#### uruchomienie aplikacji

`mvnw spring-boot:run`

#### API

<!-- markdown-swagger -->
 Endpoint                             | Method | Auth? 
 ------------------------------------ | ------ | ----- 
 `/api/account`                       | GET    | No    
 `/api/account`                       | POST   | No    
 `/api/account/change-password`       | POST   | No    
 `/api/account/reset-password/finish` | POST   | No    
 `/api/account/reset-password/init`   | POST   | No    
 `/api/account/sessions`              | GET    | No    
 `/api/account/sessions/{series}`     | DELETE | No    
 `/api/activate`                      | GET    | No    
 `/api/authenticate`                  | GET    | No    
 `/api/images`                        | GET    | No    
 `/api/images`                        | POST   | No    
 `/api/images`                        | PUT    | No    
 `/api/images/{id}`                   | GET    | No    
 `/api/images/{id}`                   | DELETE | No    
 `/api/lat-lngs`                      | GET    | No    
 `/api/lat-lngs`                      | POST   | No    
 `/api/lat-lngs`                      | PUT    | No    
 `/api/lat-lngs/{id}`                 | GET    | No    
 `/api/lat-lngs/{id}`                 | DELETE | No    
 `/api/register`                      | POST   | No    
<!-- /markdown-swagger -->

#### Opis serwisów

# Opis serwisów:

getOffers()
Metoda pobierająca wszystkie dostępne oferty w formie listy/tablicy obiektów Offer   
Przykladowy url endpoint:  'http://localhost:8080/rest/getOffers'

```
getOffers(searchCriteria: SearchCriteria): Observable<Offer[]> {   
return this.http.post<Offer[]>('http://localhost:8080/rest/getOffers', searchCriteria);  
}  
```

getCategories()
Pobiera wartości ze słownika Kategorii, zwraca liste tablice obiektów typu Category   
Przykladowy url endpoint: 'http://localhost:8080/rest/getCategories'

```
getCategories(): Observable<Category[]> {   
return this.http.get<Category[]>('http://localhost:8080/rest/getCategories');   
}
```

addOffer()
Dodaje oferte, wysyła obiekt typu Offer, zwraca ? (narazie void, kod 200 sukces 400/500 błąd)  
Przykladowy url endpoint: 'http://localhost:8080/rest/addOffer'

```
addOffer(body:Offer): Observable<void> {   
return this.http.post<void>('http://localhost:8080/rest/addOffer', body);   
}  
```

increaseOfferViewedCounter()
Dodaje oferte, wysyła obiekt typu Offer, moze tez byc samo ID o ile tak do tego podejdziemy ,zwraca ? (narazie void, kod
200 sukces 400/500 błąd)  
Przykladowy url endpoint: 'http://localhost:8080/rest/increaseOfferViewedCounter'

```
increaseOfferViewedCounter(body: Offer): Observable<void> {  
return this.http.post<void>('http://localhost:8080/rest/increaseOfferViewedCounter', body);  
}
```
saveUserPasswordChange()
Metoda pozwalająca na zmianę hasła użytkownika, wysyła obiekt PasswordChangeRequestBody, zwraca void
Przykladowy url endpoint: 'http://localhost:8080/rest/changePassword'

```
saveUserPasswordChange(passwordChangeRequestBody: PasswordChangeRequestBody):Observable<void>{
 return this.http.post<void>('http://localhost:8080/rest/changePassword', passwordChangeRequestBody);
 }
```
saveUserEmailChange() pozwala na zmianę emaila użytkownika, wysyła obiekt EmailChangeRequestBody, zwraca void
Przykladowy url endpoint: 'http://localhost:8080/rest/changeEmail'

```
saveUserEmailChange(emailChangeRequestBody: EmailChangeRequestBody): Observable<void>{
 return this.http.post<void>('http://localhost:8080/rest/changeEmail', emailChangeRequestBody); 
}
```

saveUserAddressDetailsChange() pozwala na zmianę danych adresowych użytkownika, wysyła AddressDetails, zwraca void
Przykladowy url endpoint: 'http://localhost:8080/rest/changeAddressDetails'

```
saveUserAddressDetailsChange(addressDetailsRequestBody: AddressDetails): Observable<void> {
return this.http.post<void>('http://localhost:8080/rest/changeAddressDetails', addressDetailsRequestBody); 
}
```

getAddressDetails() pobiera dane adresowe użytkownika, wysyła id użytkownika, zwraca obiekt AddressDetails
Przykladowy url endpoint: 'http://localhost:8080/rest/getAddressDetails'
```
getAddressDetails(userID: number): Observable<AddressDetails> {
return this.http.post<AddressDetails>('http://localhost:8080/rest/getAddressDetails', userID);
}
```

getMessages() pobiera wiadomosci wymienione z drugim użytkownikiem, wysyła id użytkownika i rozmówcy w formie obiektu
GetMessagesRequestBody, zwraca listę(tablice) obiektów typu Message
Przykladowy url endpoint: 'http://localhost:8080/rest/getMessages'

```
getMessages(secondUserId: number): Observable<Message[]> {
return this.http.post<Message[]>('http://localhost:8080/rest/getMessages',new GetMessagesRequestBody(this.userContextService.user.id, secondUserId)); //this.userContextService.userId
}
```

sendMessage() pozwala wysłac wiadomość do użytkownika: właściciela ogłoszenia, wysyła SendMessageRequestBody, zwraca
void
Przykladowy url endpoint: 'http://localhost:8080/rest/sendMessage'
```
sendMessage(message: SendMessageRequestBody): Observable<void> {
return this.http.post<void>('http://localhost:8080/rest/sendMessage/', message);
}
```

pozwala na pobranie listy użytkowników z którymi użytkownik wymieniał wiadomości, wysyła id zalogowanego użytkownika,
zwraca listę (tablic) uiżytkowników
Przykladowy url endpoint: 'http://localhost:8080/rest/getUsers'
```
getConversationUsers(userID: number) {
/*  return this.http.post<User[]>('http://localhost:8080/rest/getUsers', this.userContextService.userID); //this.userContextService.userID*/
}
```

pozwala na zalogowanie się, wysyła login i hasło w formie LoginUserRequestBody, zwraca obiekt User, zawierający minimum
id i hasło
Przykladowy url endpoint: 'http://localhost:8080/rest/login'
```

loginUser(value: LoginUserRequestBody): Observable<User> {
return this.http.post<User>('http://localhost:8080/rest/login', value);
}
```

pozwala na rejestrację użytkownika, wysyła RegisterUserRequestBody zwraca obiekt User, ale może zwracać cokolwiek, bo i
tak automatycznie po poprawnymn zalogowaniu wysyłam reqeust logujący użytkownika.
Przykladowy url endpoint: 'http://localhost:8080/rest/register'
```
registerUser(value: RegisterUserRequestBody): Observable<User> {
return this.http.post<User>('http://localhost:8080/rest/register', value);
}
```

# Opis modelu:

Category:  
Kategoria używana w polu 'Kategoria', zawiera jedno pole 'Name' typu String class Kategoria { String name; }

Notification:  
Klasa reprezentująca powiadomienie, prawdopodobnie nie będzie używanam, zawiera tylko treść

```
class Notification {
String body; 
}
```

Offer:  
Klasa reprezentująca oferte, używana na liście ofert, przy dodawaniu oferty, wywswietlaniu podglądu składa się z
nastepujących pól:

```
class Offer {  
Double/BigDecimal price; //cena   
Date publishDate; //data wystawienia   
String location; //lokalizacja
String address = '';//adres, cos w stylu Slaskie, Katowice 42-200
String category = ''; //kategoria, przechowywana jako sama nazwa z obiektu Category, ale może tez być obiekt Category, o ile typ Category się nie rozbuduje 
String title; //tytuł   
String owner // własciciel    
String description; // opis    
Int/Long viewedCounter = 0; //liczba wyswietlen    
Image[]/ArrayList < Image > photos; // Tablica/Lista zdjęć, pierwsze z nich jest zdjęciem głownym  
LatLng location; // wspolrzedne ogloszenia// obiekt LatLng to po prostu {Double lat; Double lng;}
Long/Int id; // jednak wygodniej bedzie operowac ID'kiem   
}
```

PagingInfo klasa z ustawieniami paginacji i sortowania:

```
class PagingInfo {  
Int/Long currentPage = 1;   
Int/Long pageSize = 10;   
String/JakisEnum sortField = 'Najnowsze'; //enum mozliwe wartosci 'Najnowsze' 'Najstarsze', 'Najdrozsze', 'Najtansze'
?   
Int/Long totalPages;    
}
```

SearchCriteria klasa reprezentującą kryteria wyszukiwania:

~~~
class SearchCriteria {   
String title;  //tytuł ogłoszenia
Category category;  //kategoria 
String location;  //to wlasciwie powinno sie nazywać address bo to adres w formie Wojewodztwo, miasto, kod pocztowy, czy cos
String description;  //opis, wiadomo
Date publishDateFrom;  //data publikacji od
Date publishDateTo;  //data publikacji do
Double/BigDecimal minPrice = 0;  //cena min
Double/BigDecimal maxPrice = 0;  //cena max
Int/Long dataPerPage = 25;  //ogloszen na strone
String sortOrder;  // sortowanie, posiada 4 mozliwe wartosci, chyba powinnismy to zapakować w enuma, albo klase ze stałymi, mozliwe wartosci: 
Najnowsze,
Najstarsze,
Najtansze,
Najdrozsze.
}
~~~

Message - klasa reprezentująca wiadomości wymieniane pomiędzy kupującym i sprzedającaym:

```
 String content; //tresc wiadomosci
 Date sent; // data wysłania
 String author; //autor, tu byc moze sama nazwa nie wystarczyc i trzeba
 String offerTitle; //tytul ogloszenia
```
SendMessageRequestBody - klasa reprezentująca wiadomośc wysyłaną do własciciela ogloszenia
```
Long/Int senderID; // id uzytkownika wysyłającego wiadomosc (kupujący)
Long/Int offerID; // id ogloszenia ktorego dotyczy wiadomosc, tytuł ogloszenia będzie potrzebny stąd to id
String owner; // nazwa/imie wlasciciela ogloszenia, byc moze będzie potrzebne jeszcze jego id,
String content; // zawartosc wiadomosci
}
```


User - klasa reprezentujaca zalogowanego użytkownika, jak i po prostu uzytkownika z którym konwersował

```
   Long/Int id; //id uzytkownika
   String name; //nazwa/imie
```
User - klasa reprezentujaca zalogowanego użytkownika, jak i po prostu uzytkownika z którym konwersował

```
   Long/Int id; //id uzytkownika
   String name; //nazwa/imie
```

LatLng - klasa reprezentująca koordynaty, musiałem zbudować swoj bo był problem z konwersją i przekazywanie pomiędzy formatkami.
```
Double lat;
Double lng;
```

AddressDetails - klasa reprezentująca dane adresowe uzytkownika.
```
Double   
String (moze jakis Enuma?) accountType; //0 - Osoba prywatna, 1 - Firma
String name; // nazwa, czyli imię i nazwisko lub nazwa firmy
String street; // ulica
String zipCode; // kod pocztowy w formacie XX-XXX (walidowane :))
String city; // miasto
String NIP; // NIP, null dla osób prywatnych
String phoneNumber; //numer telefonu, format niewalidowany
```
EmailChangeRequestBody - klasa pozwalajaca zmiane email
```
 {
String currentEmail;  //aktualny email  po stronie backendu wypadałoby sprawdzić czy currentMail sie zgadza z aktualnym, wiadomo
String newEmail; //nowy email
```
PasswordChangeRequestBody - klasa pozwalajaca zmiane hasla, tak samo jak wyżej
```
 {
PasswordChangeRequestBody {
String currentPassword; //aktualne haslo
String newPassword; //nowe haslo
}
```
LoginUserRequestBody - Klasa reprezentująca dane logowania użytkownika
```
String login; 
String password;
```

RegisterUserRequestBody jak wyżej, rozszerzone o maila i nazwę,
```
String login;
String password;
String imie; //albo nazwa w przypadku firmy
String email; // format cokolwiek@cokolwiek 
```


#### License
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
