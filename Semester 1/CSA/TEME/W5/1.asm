bits 32 ; assembling for the 32 bits architecture

global start        

extern exit
import exit msvcrt.dll


;d-(7-a*b+c)/a-6+x/2

; a,c-byte
; b-word
; d-doubleword
; x-qword
segment data use32 class=data
    a db 3
    b dw 3
    c db 3
    d dd 7
    x dq 3
    aux dd 0


segment code use32 class=code
    start:
        MOV AL, [a] ; move a to AL 
        CBW         ; convert a to word
        IMUL word [b]; DX:AX = a*b
        
        ; EAX <- DX:AX
        PUSH DX
        PUSH AX
        POP EAX
        
        MOV EBX, EAX
        MOV AX, 7
        CWDE
        SUB EAX, EBX
        MOV EBX, EAX
        MOV AL, [c]
        CBW
        CWDE
        ADD EAX, EBX
        
        MOV [aux], EAX
        
        MOV AL, [a]
        cbw
        mov CX, AX
        
        MOV AX, [aux]
        MOV DX, [aux+2]
        
        IDIV CX
        
        MOV EBX, [d]
        CWDE
        SUB EBX, EAX
        SUB EBX, 6
        
        MOV [b], BX
        
        MOV EAX, [x]
        MOV ECX, 2
        
        ;IDIV ECX
        
        MOV EAX, [x]
        MOV EDX, [x+4]
        
        MOV BX, 2
        
        IDIV BX
        
        ADD EAX, [b]
        
        
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
