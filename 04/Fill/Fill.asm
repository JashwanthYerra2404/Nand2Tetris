// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, 
// the screen should be cleared.

//// Replace this comment with your code.
(BEGIN)
@KBD
D=M
@BLACK
D;JNE
@WHITE
D;JEQ

(BLACK)
@counter 
M=-1
(LOOP)
@counter
M=M+1
D=M
@SCREEN
A=D+A
M=-1
@8191
D=D-A
@LOOP
D;JNE       // Finish painting the screen black
@BEGIN
0;JMP

(WHITE)
@counterwhite     // Paint the screen white
M=-1
(LOOPWHITE)
@counterwhite
M=M+1
D=M
@SCREEN
A=D+A
M=0
@8191
D=D-A
@LOOPWHITE
D;JNE       // Finish painting the screen white

@BEGIN      // Jump back to BEGIN
0;JMP
