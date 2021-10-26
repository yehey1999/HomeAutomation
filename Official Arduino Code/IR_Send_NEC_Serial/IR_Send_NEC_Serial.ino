//CAPSTONE

#include <IRremote.h>

//IR LED on PIN3
String readString;
IRsend irsend;



void setup()
{
  Serial.begin(9600);


  pinMode(4, OUTPUT);//Light3 pin
  pinMode(5, OUTPUT);//AC pin
  pinMode(6, OUTPUT);//Door Lock
  pinMode(7, OUTPUT);//Light1 pin
  pinMode(8, OUTPUT);//Light2 pin
  pinMode(9, OUTPUT);//Light3 pin
  pinMode(10, OUTPUT);//AC pin
  pinMode(11, OUTPUT);//Door Lock

  digitalWrite(4,HIGH);
  digitalWrite(5,HIGH);
  digitalWrite(6,HIGH);
  digitalWrite(7,HIGH);
  digitalWrite(8,HIGH);
  digitalWrite(9,HIGH);
  digitalWrite(10,HIGH);
  digitalWrite(11,HIGH);
  
}

void loop() {
  
  while (Serial.available()) {
    delay(3);  
    char c = Serial.read();
    readString += c; 
  } 

  
  if (readString.length() >0) {
    Serial.println(readString);

//=======================================
//Home Automation Lights and Appliances
//=======================================
 if (readString == "A")     //Text from android
    {
    digitalWrite(4,LOW);
    }
     if (readString == "a")     //Text from android
    {
    digitalWrite(4,HIGH);
    }

     if (readString == "B")     //Text from android
    {
    digitalWrite(5,LOW);
    }
     if (readString == "b")     //Text from android
    {
    digitalWrite(5,HIGH);
    }

     if (readString == "C")     //Text from android
    {
    digitalWrite(6,LOW);
    }
     if (readString == "c")     //Text from android
    {
    digitalWrite(6,HIGH);
    }

     if (readString == "D")     //Text from android
    {
    digitalWrite(7,LOW);
    }
     if (readString == "d")     //Text from android
    {
    digitalWrite(7,HIGH);
    }

     if (readString == "E")     //Text from android
    {
    digitalWrite(8,LOW);
    }
     if (readString == "e")     //Text from android
    {
    digitalWrite(8,HIGH);
    }

     if (readString == "F")     //Text from android
    {
    digitalWrite(9,LOW);
    }
     if (readString == "f")     //Text from android
    {
    digitalWrite(9,HIGH);
    }

     if (readString == "G")     //Text from android
    {
    digitalWrite(10,LOW);
    }
     if (readString == "g")     //Text from android
    {
    digitalWrite(10,HIGH);
    }

     if (readString == "H")     //Text from android
    {
    digitalWrite(11,LOW);
    }
     if (readString == "h")     //Text from android
    {
    digitalWrite(11,HIGH);
    }
    if (readString == "Z")     //Text from android
    {
    digitalWrite(4,HIGH);
    digitalWrite(5,HIGH);
    digitalWrite(6,HIGH);
    digitalWrite(7,HIGH);
    digitalWrite(8,HIGH);
    digitalWrite(9,HIGH);
    digitalWrite(10,HIGH);
    digitalWrite(11,HIGH);
    }

//=============================
//EPSON PROPJECTOR    
//=============================
    if (readString == "projepsonpower")     //Text from android
    {
    irsend.sendNEC(0xC1AA09F6, 32);
    }
    if (readString == "projepsonmenu")    //Text from android
    {
      irsend.sendNEC(0xC1AA59A6, 32);
    }
    if (readString == "projepsonleft")  //Text from android
    {
      irsend.sendNEC(0xC1AACD32, 32);
    }
        if (readString == "projepsonup")     //Text from android
    {
      irsend.sendNEC(0xC1AA0DF2, 32);
    }
        if (readString == "projepsonok")     //Text from android
    {
      irsend.sendNEC(0xC1AAA15E, 32);
    }
        if (readString == "projepsondown")     //Text from android
    {
      irsend.sendNEC(0xC1AA4DB2, 32);
    }
        if (readString == "projepsonexit")     //Text from android
    {
      irsend.sendNEC(0xC1AA21DE, 32);
    }
        if (readString == "projepsoninput")     //Text from android
    {
      irsend.sendNEC(0xC1AAB946, 32);
    }
        if (readString == "projepsonright")     //Text from android
    {
      irsend.sendNEC(0xC1AA8D72, 32);
    }

//=================================================
//SHARP TV
//=================================================

//=================================================
//TOSHIBA TV
//=================================================
  if (readString == "tvtoshibapower") {
      irsend.sendNEC(0x2FD48B7, 32);
    }

      if (readString == "tvtoshibainput") {
      irsend.sendNEC(0x2FDF00F, 32);
    }

      if (readString == "tvtoshiba1") {
      irsend.sendNEC(0x2FD807F, 32);
    }

      if (readString == "tvtoshiba2") {
      irsend.sendNEC(0x2FD40BF, 32);
    }

      if (readString == "tvtoshiba3") {
      irsend.sendNEC(0x2FDC03F, 32);
    }

      if (readString == "tvtoshiba4") {
      irsend.sendNEC(0x2FD20DF, 32);
    }

      if (readString == "tvtoshiba5") {
      irsend.sendNEC(0x2FDA05F, 32);
    }

      if (readString == "tvtoshiba6") {
      irsend.sendNEC(0x2FD609F, 32);
    }

      if (readString == "tvtoshiba7") {
      irsend.sendNEC(0x2FDE01F, 32);
    }

      if (readString == "tvtoshiba8") {
      irsend.sendNEC(0x2FD10EF, 32);
    }

      if (readString == "tvtoshiba9") {
      irsend.sendNEC(0x2FD906F, 32);
    }

      if (readString == "tvtoshiba0") {
      irsend.sendNEC(0x2FD00FF, 32);
    }

      if (readString == "tvtoshibamute") {
      irsend.sendNEC(0x2FD08F7, 32);
    }

      if (readString == "tvtoshibamenu") {
      irsend.sendNEC(0x2FDB847, 32);
    }

      if (readString == "tvtoshibavolup") {
      irsend.sendNEC(0x2FD58A7, 32);
    }

      if (readString == "tvtoshibavoldown") {
      irsend.sendNEC(0x2FD7887, 32);
    }

      if (readString == "tvtoshibachup") {
      irsend.sendNEC(0x2FDD827, 32);
    }

      if (readString == "tvtoshibachdown") {
      irsend.sendNEC(0x2FDF807, 32);
    }

    
    
//=================================================
//PENSONIC DVD
//=================================================

     if (readString == "dvdpensoniceject") {
      irsend.sendNEC(0xFFB04F, 32);
    }

     if (readString == "dvdpensonic0") {
      irsend.sendNEC(0xFF22DD, 32);
    }

     if (readString == "dvdpensonic1") {
      irsend.sendNEC(0xFFF00F, 32);
    }

     if (readString == "dvdpensonic2") {
      irsend.sendNEC(0xFFF20D, 32);
    }

     if (readString == "dvdpensonic3") {
      irsend.sendNEC(0xFF728D, 32);
    }

     if (readString == "dvdpensonic4") {
      irsend.sendNEC(0xFFD02F, 32);
    }

     if (readString == "dvdpensonic5") {
      irsend.sendNEC(0xFFD22D, 32);
    }

     if (readString == "dvdpensonic6") {
      irsend.sendNEC(0xFF52AD, 32);
    }

     if (readString == "dvdpensonic7") {
      irsend.sendNEC(0xFF12ED, 32);
    }

     if (readString == "dvdpensonic8") {
      irsend.sendNEC(0xFF50AF, 32);
    }

     if (readString == "dvdpensonic9") {
      irsend.sendNEC(0xFF926D, 32);
    }

     if (readString == "dvdpensonictenup") {
      irsend.sendNEC(0xFF609F, 32);
    }

     if (readString == "dvdpensonicvoldown") {
      irsend.sendNEC(0xFF18E7, 32);
    }

     if (readString == "dvdpensonicmute") {
      irsend.sendNEC(0xFF08F7, 32);
    }

     if (readString == "dvdpensonicup") {
      irsend.sendNEC(0xFF629D, 32);
    }

     if (readString == "dvdpensonicstop") {
      irsend.sendNEC(0xFF40BF, 32);
    }

     if (readString == "dvdpensonicplay") {
      irsend.sendNEC(0xFFC03F, 32);
    }

     if (readString == "dvdpensonicpause") {
      irsend.sendNEC(0xFFC03F, 32);
    }

     if (readString == "dvdpensonicdown") {
      irsend.sendNEC(0xFF6897, 32);
    }

     if (readString == "dvdpensonicright") {
      irsend.sendNEC(0xFFA857, 32);
    }

     if (readString == "dvdpensonicleft") {
      irsend.sendNEC(0xFFE21D, 32);
    }

     if (readString == "dvdpensonicok") {
      irsend.sendNEC(0xFFAA55, 32);
    }

     if (readString == "dvdpensonicprev") {
      irsend.sendNEC(0xFF8A75, 32);
    }

     if (readString == "dvdpensonicnext") {
      irsend.sendNEC(0xFF8877, 32);
    }

     if (readString == "dvdpensonicrew") {
      irsend.sendNEC(0xFF827D, 32);
    }

     if (readString == "dvdpensonicff") {
      irsend.sendNEC(0xFF807F, 32);
    }

     if (readString == "dvdpensonicmenu") {
      irsend.sendNEC(0xFF5AA5, 32);
    }

     if (readString == "dvdpensonicpower") {
      irsend.sendNEC(0xFF32CD, 32);
    }
       if (readString == "dvdpensonicvolup") {
      irsend.sendNEC(0xFF28D7, 32);
    }


    

//=================================================
//MAGUS DVD
//=================================================
 if (readString == "dvdmaguseject") {
      irsend.sendNEC(0xFF7887, 32);
    }

     if (readString == "dvdmagus0") {
      irsend.sendNEC(0xFF2AD5, 32);
    }

     if (readString == "dvdmagus1") {
      irsend.sendNEC(0xFFD02F, 32);
    }

     if (readString == "dvdmagus2") {
      irsend.sendNEC(0xFFC837, 32);
    }

     if (readString == "dvdmagus3") {
      irsend.sendNEC(0xFFD827, 32);
    }

     if (readString == "dvdmagus4") {
      irsend.sendNEC(0xFFF00F, 32);
    }

     if (readString == "dvdmagus5") {
      irsend.sendNEC(0xFFE817, 32);
    }

     if (readString == "dvdmagus6") {
      irsend.sendNEC(0xFFF807, 32);
    }

     if (readString == "dvdmagus7") {
      irsend.sendNEC(0xFF12ED, 32);
    }

     if (readString == "dvdmagus8") {
      irsend.sendNEC(0xFF0AF5, 32);
    }

     if (readString == "dvdmagus9") {
      irsend.sendNEC(0xFF1AE5, 32);
    }

     if (readString == "dvdmagustenup") {
      irsend.sendNEC(0xFF2AD5, 32);
    }

     if (readString == "dvdmagusvolup") {
      irsend.sendNEC(0xFF827D, 32);
    }

     if (readString == "dvdmagusvoldown") {
      irsend.sendNEC(0xFFA25D, 32);
    }

     if (readString == "dvdmagusmute") {
      irsend.sendNEC(0xFF42BD, 32);
    }

     if (readString == "dvdmagusup") {
      irsend.sendNEC(0xFFF20D, 32);
    }

     if (readString == "dvdmagusstop") {
      irsend.sendNEC(0xFF9867, 32);
    }

     if (readString == "dvdmagusplay") {
      irsend.sendNEC(0xFF8877, 32);
    }

     if (readString == "dvdmaguspause") {
      irsend.sendNEC(0xFF906F, 32);
    }

     if (readString == "dvdmagusdown") {
      irsend.sendNEC(0xFFEA15, 32);
    }

     if (readString == "dvdmagusright") {
      irsend.sendNEC(0xFFDA25, 32);
    }

     if (readString == "dvdmagusleft") {
      irsend.sendNEC(0xFFD22D, 32);
    }

     if (readString == "dvdmagusok") {
      irsend.sendNEC(0xFFCA35, 32);
    }

     if (readString == "dvdmagusprev") {
      irsend.sendNEC(0xFFA857, 32);
    }

     if (readString == "dvdmagusnext") {
      irsend.sendNEC(0xFFB847, 32);
    }

     if (readString == "dvdmagusrew") {
      irsend.sendNEC(0xFFA05F, 32);
    }

     if (readString == "dvdmagusff") {
      irsend.sendNEC(0xFFB04F, 32);
    }

     if (readString == "dvdmagusmenu") {
      irsend.sendNEC(0xFF00FF, 32);
    }

     if (readString == "dvdmaguspower") {
      irsend.sendNEC(0xFF40BF, 32);
    }


//=====================================
    
    readString="";
  }
}
