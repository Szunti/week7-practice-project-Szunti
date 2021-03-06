# Struktúraváltó résztvevők haladását nyomonkövető alkalmazás

## Vízió

A projektmunkát egy valós igény keltette életre. A Struktúraváltó projekt során a résztvevők előrehaladásának ellenőrzése, tárolása, lekérdezése, áttekintése elég körülményes, különböző rendszerekben történik.

### Funkcionális követelmények

A következőket kell karbantartani:

* Évfolyamokat (`TrainingClass`)
* Résztvevőket (`Student`), egy résztvevő akár több évfolyamon is szerepelhet
* Tanmeneteket (`Syllabus`)
* A tanmenetekhez modulok (`Module`), ahhoz leckék (`Lesson`) tartoznak
* A tanmenetet évfolyamhoz lehet rendelni
* Be lehessen jelölni, hogy melyik résztvevő melyik leckét dolgozta fel: megnézte a videót, és elkészítette a
  gyakorlati feladatot

A különböző adatokat kell nyilvántartani:

#### Évfolyam

* Elnevezés (nem üres, max. 255 karakter)
* Kezdés dátuma (tetszőleges)
* Befejezés dátuma (tetszőleges, később legyen, mint a kezdés)

Lehet listázni, lekérdezni, létrehozni, mindhárom attribútumot módosítani, törölni.

#### Résztvevők

* Név (nem üres, max. 255 karakter)
* E-mail cím (nem üres, max. 255 karakter)
* GitHub felhasználónév (nem kötelező, max. 255 karakter)
* Megjegyzés

A résztvevőket is lehet felvenni, lekérdezni, listázni, módosítani és törölni az `/api/students` végponton.

#### Évfolyam - résztvevő kapcsolat

Egy résztvevő be tud iratkozni egy tanfolyamra. Ezt a beiratkozás osztály/tábla tartalmazza (`registration`).

A beiratkozásnak vannak státuszai: aktív (`ACTIVE`), kilépés alatt (`EXIT_IN_PROGRESS`), kilépett (`EXITED`).  
Hiszen egy résztvevő egy évfolyamon lehet aktív, míg egy másikon kilépett.

Beiratkozás történhet a `/trainingclasses/{id}/registrations` címen. Meg kell adni a résztvevő azonosítóját.  
Itt le lehet kérdezni az évfolyamra beiratkozottakat (a résztvevőkről csak az id-ját, nevét és státuszát adja vissza).

Egy résztvevő beiratkozásait is le lehet kérdezni a `/students/{id}/registrations` címen.  
Csak az évfolyamok id-ját és nevét adja vissza.

Az évfolyam és a résztvevő között nincs közvetlen kapcsolat.

#### Tanmenet

* Név (nem üres, max. 255 karakter)

Egy tanmenet több évfolyamhoz is tartozhat, egy évfolyamhoz egy tanmenet!

Lehet listázni, lekérdezni, létrehozni, minden adatot módosítani, törölni.

Mindezt a `/syllabuses` címen.

A tanmenetet a `/trainingclasses/{id}/syllabuses` címen lehet az évfolyamhoz rendelni.  
Amikor létrehozod az évfolyamot, a hozzá tartozó tanmenet még üres.  
Ezen a címen lehet POST-tal felvenni, a megfelelő tanmenet id-jának beküldésével. Módosítani lehet PUT-tal, ekkor másik tanmenetet lehet hozzá rendelni.

#### Modul

* Cím (nem üres, max. 255 karakter)
* URL (nem üres, max. 255 karakter)

Egy tanmenethez több modul is tartozhat. Egy modul több tanmenethez is tartozhat. Ne mutasson vissza a modul a tanmenetre.

Lehet listázni, lekérdezni, létrehozni, minden adatot módosítani, törölni.
A `/modules` címen adminisztrálható.

A modult a tanmenethez a `/syllabuses/{id}/modules` címen lehessen hozzáadni.

#### Lecke

* Cím (nem üres, max. 255 karakter)
* URL (nem üres, max. 255 karakter)

Egy modulhoz több lecke is tartozhat. A lecke visszahivatkozhat a modulra.

Lehet listázni, lekérdezni, létrehozni, minden adatot módosítani, törölni.

A `/modules/{id}/lessons` címen adminisztrálható.

#### Lecke elvégzése

* Melyik résztvevő
* Melyik leckét
* Videót, gyakorlati feladatot, vagy mindkettőt

Szintén egy kapcsoló entitásra van szükség, ami hivatkozik egy résztvevőre és egy leckére (`LessonCompletion`). 
Valamint az adatai: 

* Videót megnézte-e (nem `boolean`, hanem enum: `COMPLETED`, `NOT_COMPLETED`)
* Gyakorlati feladatot elvégezte-e (nem `boolean`, hanem enum: `COMPLETED`, `NOT_COMPLETED`)
* Videó elvégzésének dátuma
* Gyakorlat elvégzésének dátuma
* Commit URL-je (lehet üres, max. 255 karakter)

Elérhető a `/students/{id}/lessioncompletition` címen.

Lehet listázni, lekérdezni, létrehozni, minden adatot módosítani (kivéve a résztvevőt), törölni.

### Nem-funkcionális követelmények

Klasszikus háromrétegű alkalmazás, MySQL adatbázissal, Java Spring backenddel, REST webszolgáltatásokkal.

### Linkek:
UML: [google drive](https://drive.google.com/file/d/1MMEiW0rxklmGgc5TuU1gJrPCKAiWPy_X/view?usp=sharing)