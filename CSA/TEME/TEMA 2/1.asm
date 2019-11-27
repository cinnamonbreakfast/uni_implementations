bits 32 ; assembling for the 32 bits architecture

global start        

extern exit
import exit msvcrt.dll


; a, b, c, d : byte
; (c+d+d)-(a+a+b)
segment data use32 class=data
    a db 10
    b db 5
    c db 3
    d db 7
    x db 0


segment code use32 class=code
    start:
        MOV AL, [c]
        ADD AL, [d]
        ADD AL, [d]
        
        MOV AH, [a]
        ADD AH, [a]
        ADD AH, [b]
        
        SUB AL, AH
        MOV [x], AL
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
