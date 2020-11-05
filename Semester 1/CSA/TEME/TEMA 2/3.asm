bits 32 ; assembling for the 32 bits architecture

global start        

extern exit
import exit msvcrt.dll


; a, b, c : byte
; (a+a)-(b+b)-c
segment data use32 class=data
    a db 10
    b db 5
    c db 3
    x db 0


segment code use32 class=code
    start:
        MOV AH, [a]
        ADD AH, [a]
        
        MOV AL, [b]
        ADD AL, [b]
        
        SUB AH, AL
        SUB AH, [c]
        MOV [x], AH
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
