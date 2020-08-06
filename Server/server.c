#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h>
#include "constansts.h"
#include "Structures.h"
#include <pthread.h>


struct  Game game;

int list [LARGE_LIST];
/*
This method is to recive the message from client, the menssage should be like command,value
*/
void *ReciveMessage(void * socket){
    int new_socket= *(int*)socket; //get the number of socket
    while(1){
        char buffer[BUFFERSIZE]; // define the length of buffer, BUFFERSIZE is constansts.h file
        int x; // Indicate the end of menssage
        if ((x=read( new_socket , buffer, BUFFERSIZE))>0){
        printf("%s\n", buffer);

            buffer[x]='\0'; // \0 is indicates the end
            fflush(stdout); // Is to clear the buffer
            Parse(buffer, new_socket);    // parse the mensage
        }

        sleep(1);
    }

}
// This method is to send the menssage from administrator to client to set the parameters
void *SendMessageFromAdministrator(void * socket){
    int sock= *(int*)socket;
    char message[5];
    
    char *instruction = "Digite la opción que desea realizar. \n 1) Crear Fantasma \n 2) Crear Pastilla \n 3) Crear Fruta \n 4) Asignar Velocidad a Fantasmas \n 5) Asignar valores a los Dots"; 
    int opcion;
    while (game.level<=3 && game.alive==1 ){ // while the player is alive
        printf("%s\n",instruction); // indicade the instruction read
        scanf("%d", &opcion); // take the date from console
        if (opcion==1){
            char result[100] ="createghost,";
            printf("Color: 1 Blinky, 2 Pinky, 3 Inky, 4 Clyde.\n");
            scanf("%s", message);//take data from console
            strcat(result, message); // concatenate strings
            char fin[1] =".";
            strcat(result, fin); // concatenate strings
            //printf("%s\n",result);
            send(sock , result , strlen(result) , 0 ); // send the data to client
        }
        else if (opcion==2){
            char result[100] ="pill,";
            printf("Digite posición.\n");
            scanf("%s", message);
            strcat(result, message);
            //printf("%s\n",result);
            char fin[1] =".";
            strcat(result, fin); // concatenate strings
            send(sock , result , strlen(result) , 0 );
        }
        else if (opcion==3){
            char result[100]="fruit,";
            printf("Digite valor de la fruta. \n");
            scanf("%s", message);
            strcat(result, message);
            //printf("%s\n",result);
            char fin[1] =".";
            strcat(result, fin); // concatenate strings
            send(sock , result , strlen(result) , 0 );
        }
        else if (opcion==4){
            char result[100]="velocity,";
            printf("Digite la velocidad. \n");
            scanf("%s", message);
            strcat(result, message);
            printf("%s\n",result);
            char fin[1] =".";
            strcat(result, fin); // concatenate strings
            send(sock , result , strlen(result) , 0 );
        }
        else if (opcion==5){
            char result[100]="points,";
            printf("Digite el valor de los puntos.\n");
            scanf("%s", message); //take data from console
            game.valueOfPoints=atoi(message);
            strcat(result, message);
            printf("%s\n",result);
            char fin[1] =".";
            strcat(result, fin); // concatenate strings
            send(sock , result , strlen(result) , 0 );
        }
        else {
            printf("Digite un valor válido\n");
        }

        
        sleep(1); 
    }
}


int main(int argc, char const *argv[]) 
{ 
    pthread_t t1, t2; // threads are to execute send and recive simultaneusly
    InitializeGame();// set the beginings parameters

    int server_fd, new_socket, valread; 
    struct sockaddr_in address; 
    int opt = 1; //flag for socket
    int addrlen = sizeof(address); 
    char buffer[BUFFERSIZE]; 

       
    // Creating socket file descriptor 
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) 
    { 
        perror("socket failed"); 
        exit(EXIT_FAILURE); 
    } 
       
    // Forcefully attaching socket to the port 
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, 
                                                  &opt, sizeof(opt))) 
    { 
        perror("setsockopt"); 
        exit(EXIT_FAILURE); 
    } 
    address.sin_family = AF_INET; 
    address.sin_addr.s_addr = INADDR_ANY; 
    address.sin_port = htons( PORT ); 
       
    // Forcefully attaching socket to the port  
    if (bind(server_fd, (struct sockaddr *)&address,  
                                 sizeof(address))<0) 
    { 
        perror("bind failed"); 
        exit(EXIT_FAILURE); 
    } 
    if (listen(server_fd, 3) < 0) 
    { 
        perror("listen"); 
        exit(EXIT_FAILURE); 
    } 
    if ((new_socket = accept(server_fd, (struct sockaddr *)&address,  
                       (socklen_t*)&addrlen))<0) 
    { 
        perror("accept"); 
        exit(EXIT_FAILURE); 
    } 
    //valread = read( new_socket , buffer, 1024); 
    pthread_create(&t1, NULL, &ReciveMessage, &new_socket);//create threads
    pthread_create(&t2, NULL, &SendMessageFromAdministrator, &new_socket);
    //printf("%s\n",buffer ); 
    //send(new_socket , hello , strlen(hello) , 0 ); 
    //printf("Hello message sent\n"); 
    pthread_join(t1, NULL); // wait the thread, wait the main
    pthread_join(t2, NULL); 
    return 0; 
} 
void InitializeGame(){
    InitializateList();
    game.amountOfLifes=3;
    game.amountOfPoints=0;
    game.amountOfGhost=0;
    game.amountOfFruits=0;
    game.level=0;
    game.alive=1;
    game.valueOfPoints=10;
    game.level=1;
    game.velocityGhost=1;
    
}
void Parse(char message[BUFFERSIZE], int socket){
     // Extract the first token
     //Identify the correct command 
   char * token1 = strtok(message, ","); // split by ,
   char * token2= strtok(NULL, ",");// split by ,
   
   int value=atoi(token2); // convert to int 
    if (strcmp(token1,"dot") == 0){
        game.list[value]=0; // indicate the dot was eaten
        game.amountOfPoints=game.amountOfPoints+game.valueOfPoints;// add points
        if (value==163){
            if (game.level==3){ // ask if the level is the lastone
                char messageToSend[20]="won,100.";
                send(socket , messageToSend , strlen(messageToSend) , 0 );
                //printf("%s",messageToSend);
            }
            else{
                InitializateList();// reset the list 
                game.level=game.level+1; // next level
                char messageToSend[20]="level,"; 
                char levelAux[1];
                sprintf(levelAux,"%d",game.level);//convert from int to string
                strcat(messageToSend,levelAux);
                char fin[1] =".";
                strcat(messageToSend, fin); // indicate the end
                send(socket , messageToSend , strlen(messageToSend) , 0 );
                //printf("%d\n",game.level );
                //printf("Mensaje: %s\n",messageToSend);
                //printf("Pasó de nivel\n");
            }
        }
        
    }
    else if (strcmp(token1,"fruit")==0){
        game.amountOfPoints=game.amountOfPoints+value;
        //printf("Comió fruto\n");
    }
    else if (strcmp(token1,"ghost")==0){
        game.amountOfPoints=game.amountOfPoints+500;
        //printf("Comió un fantasma\n");
    }
    else if (strcmp(token1,"pacman")==0){
        game.amountOfLifes--;
        char messageToSend[20]="lifes,";
        char lifesAux[3];
        sprintf(lifesAux,"%d", game.amountOfLifes);        
        strcat(messageToSend,lifesAux);
        char fin[1] =".";
        strcat(messageToSend, fin); // indicate the end
        send(socket , messageToSend , strlen(messageToSend) , 0 );
        //printf("Murió pacman\n");
    }

    if (game.amountOfPoints>=1000){ // is to give one life
        
        game.amountOfLifes++; // add life
        printf("Una Vida más\n");
        char messageToSend[20]="lifes,";
        char lifesAux[3];
        sprintf(lifesAux, "%d", game.amountOfLifes);
        strcat(messageToSend,lifesAux);
        game.amountOfPoints=0;
        char fin[1] =".";
        strcat(messageToSend, fin); // indicate the end
        send(socket , messageToSend , strlen(messageToSend) , 0 );

        //printf("Una Vida más\n");
    }
}
void InitializateList(){  
    int val = 1; 
  
    // initializing array elements 
    for (int i = 0; i < LARGE_LIST ; i++){ 
        //if (i==10) this if is to test the victory
        game.list[i] = val; 
    } 
  
}
int CountOneInList(){//count how many 1s are in the list
    int amount=0;
    for(int i=0;i<LARGE_LIST;i++){
        if (game.list[i]==1)
            amount++;
    }
    
    return amount;
}
