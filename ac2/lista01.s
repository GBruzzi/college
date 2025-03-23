.text
.globl _start

_start:
## program 01

  li s0, 2
  li s1, 3
  li s2, 4
  li s3, 5
  add t0,s0,s1
  add t1, s2,s3
  sub s4, t0, t1
  sub s5, s0,s1
  add s6, s5, s4
  sub s1 , s4, s6

## program 02

  li s0, 1
  li t0, 5
  sub s1, t0, s0
  addi s2, s1,15

#program 03

  li s0, 3
  li s1, 4

  li t0, 15
  sub s2, t0, s0

  li t1, 67
  sub s3, t1, s1

  add t2, s2, s3
  addi s4, t2, 4

#parte 2

#program 04
  li s0, 1
  li t0, 5
  mul s1, s0, t0
  addi s2, s1, 15

#program 05
  li s0, 3
  li s1, 4

  li t0, 15
  mul s2, s0,t0

  li t1, 67
  mul s3, s1, t1

  add s4, s3, s2

  li t0,4
  mul s5, s4, t0


#program 06
  li s0, 1       
  slli s0, s0, 20 # x = 2^20 = 1 << 20

  li s1, 1       # Carregar 1 em s1
  slli s1, s1, 12 # y = 2^12 = 1 << 12

  add s2, s0, s1 # z = x + y

#program 07

  # x = 2^32 - 1 (ou seja, todos os bits 1 em uma palavra de 32 bits)
  li s0, 0xFFFFFFFF  # Carrega o valor máximo de 32 bits em s0 (x)

  li s1, 8192        

  slli s2, s1, 2     

  sub s3, s0, s2

#program 08

  ori x8, x0, 0x01       
  slli x8, x8, 1         
  ori x8, x8, 0x01       
  slli x8, x8, 2         
  ori x8, x8, 0x03       
  slli x8, x8, 4         
  ori x8, x8, 0x0F       
  slli x8, x8, 8         
  ori x8, x8, 0xFF       
  slli x8, x8, 16        
  ori x8, x8, 0xFFFF     

#program 09
  li x8, 0x12345678
  srl x9, x8, 24 
  srl x10, x8, 16
  andi x10, x10, 0xFF
  srl x11, x8, 8
  andi x11, x11, 0xFF
  andi x12, x8, 0xFF