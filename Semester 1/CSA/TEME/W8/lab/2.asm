bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, printf
import exit msvcrt.dll
import printf msvcrt.dll


; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db '123', '4', '56'

; our code starts here
segment code use32 class=code
    start:
        or word [a], 2
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
