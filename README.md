# eMuseum and Khoj
an App for the museums of Mumbai, made for Hackathon organized by ZKM
by team Short Circuits

## Note:<br>Very important as our app works only online an internet connection is required<br>As we are using free hosting, server will remain shut everyday for 1 hour

# Android App Working 
## The android app is two part:
# **eMuseum**
eMuseum is a feed reader which helps connect people and museums called eMuseum. This allows people to stay updated with activities of CSMVS and BDL from the comfort of their homes.

<p align="center">
  <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/1.png" width="250"/>
 <br>eMuseum home 
</p>
eMuseum also offers a map service where users can view museum maps, click on exhibits inside the map and view image and info regarding that exhibit.
<br>
<p align="center">
  <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/2.jpg" width="250"/>
 <br>map with points marking different exhibits which the user can interact with<br>
  <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/2.1.jpg" width="250"/>
 <br>card view showing picture of exhibit
</p>

# **Khoj**
Khoj is a game as well as a quick reference encyclopedia called Museumpedia that employs QR codes to quickly provide more information about an exhibit. The game - Around the Museum - is like a treasure hunt where the objective of the visitor is to go around the museum (literally) collecting clues. 
## Around the Museum
<p align="center">
  <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/3.png" width="250"/>
 <br>Choosing between game and museumpedia<br><br>
 <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/4.jpg" width="250"/>
 <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/5.png" width="250"/>
 <br>Game screen, enter name and start playing <br><br>
 <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/6.1.png" width="250"/>
 <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/6.2.png" width="250"/>
 <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/6.3.png" width="250"/>
 <br>Look up a clue, scan the appropriate exhibit, and gather more info and more clues<br><br> 
</p>

## Museumpedia
<p align="center">
  <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/7.png" width="250"/>
  <br>Museumpedia start screen<br><br>
  <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/8.png" width="250"/>
  <img src="https://github.com/AlwaysHungrie/eMuseum/blob/master/Screenshots/9.png" width="250"/>
  <br>Search through the museum database to know more about the most interesting artifacts<br>
 
</p>

## Server
Our server, database is hosted on 000webhost.com, under the domain [dhairyashah.000webhostapp.com](http://dhairyashah.000webhostapp.com/banana.php?id=1)
## API call
banana.php?id=[1-100] will fetch information from data set provided of 100 artifacts provided in CSMVS dataset.

For example:


[dhairyashah.000webhostapp.com/banana.php?id=1](http://dhairyashah.000webhostapp.com/banana.php?id=1) gives this response
[{"ID":"1.0",
"Title":"Devotee",
"Period":"5th CE",
"Religion":"buddhism",
"Dynasty":null,
"Type":"sculpture",
"Short_Description":"Sculpture in terracotta of man holding flower",
"Long_Description":"This sculpture is the legacy of the famous 19th Century archaeologist Henry Cousens who excavated the site of a stupa at Mirpurkhas, one of the most important and well preserved sites of the Indo-Greek Buddhist settlements. This terracotta was found leaning against the north wall of the central shrine. Curiously, this the only secular image among the large number of religious figures found at the site. The image probably represents a donor disciple who contributed towards the construction of the stupa. \r\nThe modeling is a bit heavy, but the expressive face, particularly the half-closed thoughtful eyes, the sharp arch of the eyebrows and the full lips impart an unusual charm to the figure. Look how the hair arranged with care. The ear ornaments do not match each other: the left earring is larger and has three pearl drops. Possibly this special earring indicates a position of office. (A similar custom in Tibet was prevalent till the 18th Century where high officials in the government wore a special kind of earring in one ear.) The elaborate hairdo also seems to be a mark of an important position in the state administration. \r\nThe devotee\u2019s elegantly-draped striped dhoti (lower garment) has traces of paint. The manner in which he holds the flower is reminiscent of the famous painting, Bodhisattva Padmapani, in cave no. I at Ajanta.",
"Filename":"TC-56.jpg"}]
 
