bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, scanf, printf
import exit msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a times 120 db 0
    format db '%s', 0
    len equ 120

; our code starts here
segment code use32 class=code
    start:
        push len
        push a
        push format
        call [scanf]
        
        add esp, 4*3
        
        push a
        push format
        call [printf]
        
        add esp, 4*2
        
        push len
        push a
        push format
        call [scanf]
        
        add esp, 4*3
        
        push a
        push format
        call [printf]
        
        add esp, 4*2
        
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
