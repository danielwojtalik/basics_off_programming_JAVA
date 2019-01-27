#Collections_and_maps_3

## Rozwiazanie problemu plecakowego

Z pliku Json, pobierana jest lista klientow oraz lista wszystkich dostepnych produktow. 
Klienci kupuja produkty zgodnie z ich preferencjami. To znaczy, ze kupuja wszystkie 
elementy, na ktore ich stac z kategorii, ktora wg ich preferencji jest przed innymi. 
W obrebie jednej kateogii klient preferuje te produkty, ktore maja bardziej korzystny (wiekszy) 
wspolczynnik ilosci do ceny. Po kupieniu wszystkich produktow, na ktore stac klienta z danej kategorii,
klient wg tego samego klucza przechodzi do kolejnej kategorii. Klient moze kupic tylko raz dany produkt.

### Mozliwosci programu

1. Pokaz zestawienie klientow wraz ze wszystkimi produktami jakie kupili
2. Pokaz klienta, ktory kupil najwiecej przedmiotow
3. Pokaz klienta, ktory wydal najwiecej pieniedzy
4. Pokaz zestawienie produktów wraz z liczba sprzedanych sztuk. Dodatkowo wypisz liste 
produktow najchetniej oraz najslabiej sprzedawanych.
5. Pod kazdy numer kategorii podczytaj nazwe z pliku Json i zrob zestawienie przedstawiajace popularnosc kategorii
(ile produktow z danej kategorii zostalo sprzedanych) w porzadku rosnacym
