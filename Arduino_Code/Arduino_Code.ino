/*
------------------------------------------------------------------------
                            Arduino Connect
------------------------------------------------------------------------
*/


#include <Servo.h>
#include<MFRC522.h>
#include <SPI.h>


#define RST_PIN         9          // Configurable, see typical pin layout above
#define SS_PIN          10         // Configurable, see typical pin layout above

String voice;
int relay1=3;   //Connect relay1 to pin 2
int relay2=4;   //Connect relay2 to pin 3
int relay3=5;
int relay4=6;

int servopin=2;
Servo myservo;
int servoangle=0;

MFRC522 mfrc522(SS_PIN, RST_PIN);  // Create MFRC522 instance

void setup()
{
  Serial.begin(9600);            //Set rate for communicating with phone
  SPI.begin();      // Init SPI bus
  mfrc522.PCD_Init();   // Init MFRC522
  pinMode(relay1, OUTPUT);       //Set relay1 as an output
  pinMode(relay2, OUTPUT);       //Set relay2 as an output
  pinMode(relay3, OUTPUT);
  pinMode(relay4, OUTPUT);
  
  digitalWrite(relay1, HIGH);     //Switch relay1 off
  digitalWrite(relay2, HIGH);     //Swtich relay2 off
  digitalWrite(relay3, HIGH);
  digitalWrite(relay4, HIGH);
 
  myservo.attach(servopin);

  Serial.println("Put your card on the reader...");
  Serial.println("");
}

void loop()
{
 /* 
  if ( ! mfrc522.PICC_IsNewCardPresent()) {
    return;
  }

  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial()) {
    return;
  }
  
  Serial.print("UID tag:");
  String content="";
  byte letter;
  */ 
   while(Serial.available())    //Check if there are available bytes to read
   {
     delay(10);                 //Delay to make it stable
     char c= Serial.read();    //Conduct a serial read
     if (c == '#'){
       break;                   //Stop the loop once # is detected after a word
     }
    voice+= c;                //Means voice = voice + c
    
  }
  Serial.println(voice);
  
   if (voice.length()>0){
      Serial.println(voice);
      if(voice == "*all on"){
            switchon();
      }else if(voice == "*all off" || "*all of"){
            switchoff();
      }else if(voice == "*light one on" || voice == "*light 1 on" ){   
             digitalWrite(relay1, LOW);
      }else if(voice == "*light one off" || voice == "*light 1 off" || voice == "*light one of" || voice == "*light 1 of" )
            {
             digitalWrite(relay1, HIGH);
           }else if(voice == "*light two on" || voice == "*light 2 on" || voice == "*light to on"){
              digitalWrite(relay2, LOW);
             }else if(voice == "*light two off" || voice == "*light 2 off" || voice == "*light two of" || voice == "*light 2 of" || voice == "*light to of" || voice == "*light to off"){
                digitalWrite(relay2, HIGH);
            }else if(voice == "*main light on"){
              digitalWrite(relay3, LOW);
              }else if(voice == "*main light off" || voice == "*main light of"){
              digitalWrite(relay3, HIGH);
              }else if(voice == "*fan on"){
                digitalWrite(relay4, LOW);  
              }else if(voice == "fan off" || voice == "fan of"){

                  digitalWrite(relay4, HIGH);
      }else if(voice == "*radio on" || voice == "*music on")
                  {
                   // digitalWrite(relay5, LOW);
      }else if(voice == "*radio off" || voice == "*music off" || voice == "*radio of" || voice == "*music of")
                  {
                    //digitalWrite(relay5, HIGH);
            }
      
    }
    voice="";
/*
   for(byte i=0;i<mfrc522.uid.size;i++)
 {
  Serial.print(mfrc522.uid.uidByte[i]<0x10?" 0": " ");
  Serial.print(mfrc522.uid.uidByte[i],HEX);
  content.concat(String(mfrc522.uid.uidByte[i]<0x10?" 0": " "));
  content.concat(String(mfrc522.uid.uidByte[i],HEX));
 }
 Serial.println();
 Serial.print( "Message: " );
 content.toUpperCase(); 
   if(content.substring(1)=="60 E6 6B A3")
    {
        Serial.println("Authorised Access");
        Serial.println();
        delay(500);
        //digitalWrite(LED_G,HIGH);
        myservo.write(180);
        delay(500);
        myservo.write(0);
        //digitalWrite(LED_G,LOW);
                if(servoangle<25)
                { for(servoangle = 20; servoangle < 150; servoangle++)  // Shutter Up code
                  {                                  
                   myservo.write(servoangle);              
                  delay(20);                  
                  }
                }
                delay(2000);
                if(servoangle>145){
                  
                        for(servoangle = 150; servoangle > 20; servoangle--)  // Shutter Down Code
                        {                                  
                           myservo.write(servoangle);              
                          delay(20);                  
                        }
                  
                    } 
                 delay(2000);   
    
        }else{
       Serial.println("Access Denied");
       Serial.println();
       delay(500);
    
     }
 */
}
void switchon()               //Function for turning on relays(Active low relays)(LOW means HIGH)
{ digitalWrite(relay1, LOW);     //Switch relay1 on
  digitalWrite(relay2, LOW);     //Swtich relay2 on
  digitalWrite(relay3, LOW);
  digitalWrite(relay4, LOW);
  
}
void switchoff()              //Function for turning on relays
{
  digitalWrite(relay1, HIGH);     //Switch relay1 off
  digitalWrite(relay2, HIGH);     //Swtich relay2 off
  digitalWrite(relay3, HIGH);
  digitalWrite(relay4, HIGH);
 
 }
/*

You can add any function you want depending on how many devices you have hooked up.
For example you could have a function called 'cinema' which would dim the lights and
turn the TV on. You can have as many as you have pins on your arduino.
For my relay 'LOW' turns off and 'HIGH' turns on
The outline to follow is this:
   
   void ......()
   {
     digitalWrite(...., LOW/HIGH);
     digitalWrite(...., LOW/HIGH);
   }
   
*/
