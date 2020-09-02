(module
 (type $FUNCSIG$vi (func (param i32)))
 (type $FUNCSIG$iii (func (param i32 i32) (result i32)))
 (type $FUNCSIG$vf (func (param f32)))
 (type $FUNCSIG$fii (func (param i32 i32) (result f32)))
 (type $FUNCSIG$vd (func (param f64)))
 (type $FUNCSIG$dii (func (param i32 i32) (result f64)))
 (type $FUNCSIG$iiii (func (param i32 i32 i32) (result i32)))
 (import "env" "f32sum" (func $f32sum (param i32 i32) (result f32)))
 (import "env" "f64sum" (func $f64sum (param i32 i32) (result f64)))
 (import "env" "i32sum" (func $i32sum (param i32 i32) (result i32)))
 (import "env" "i64sum" (func $i64sum (param i32 i32) (result i32)))
 (import "env" "memset" (func $memset (param i32 i32 i32) (result i32)))
 (import "env" "print_f32" (func $print_f32 (param f32)))
 (import "env" "print_f64" (func $print_f64 (param f64)))
 (import "env" "print_i32" (func $print_i32 (param i32)))
 (import "env" "print_i64" (func $print_i64 (param i32)))
 (import "env" "puts" (func $puts (param i32)))
 (table 0 anyfunc)
 (memory $0 1)
 (data (i32.const 16) "heyy\00")
 (data (i32.const 32) "\00\00\00\00\01\00\00\00\02\00\00\00\03\00\00\00\04\00\00\00")
 (data (i32.const 64) "\00\00\00\00\01\00\00\00\02\00\00\00\03\00\00\00\04\00\00\00")
 (data (i32.const 96) "\00\00\00\00\00\00\80?\00\00\00@\00\00@@\00\00\80@")
 (export "memory" (memory $0))
 (export "main" (func $main))
 (func $main (; 10 ;) (result i32)
  (local $0 i32)
  (i32.store offset=4
   (i32.const 0)
   (tee_local $0
    (i32.sub
     (i32.load offset=4
      (i32.const 0)
     )
     (i32.const 144)
    )
   )
  )
  (call $puts
   (i32.const 16)
  )
  (i32.store
   (i32.add
    (i32.add
     (get_local $0)
     (i32.const 112)
    )
    (i32.const 16)
   )
   (i32.load offset=48
    (i32.const 0)
   )
  )
  (i64.store offset=120
   (get_local $0)
   (i64.load offset=40
    (i32.const 0)
   )
  )
  (i64.store offset=112
   (get_local $0)
   (i64.load offset=32
    (i32.const 0)
   )
  )
  (call $print_i32
   (call $i32sum
    (i32.add
     (get_local $0)
     (i32.const 112)
    )
    (i32.const 5)
   )
  )
  (i32.store
   (i32.add
    (i32.add
     (get_local $0)
     (i32.const 80)
    )
    (i32.const 16)
   )
   (i32.load offset=80
    (i32.const 0)
   )
  )
  (i64.store offset=88
   (get_local $0)
   (i64.load offset=72
    (i32.const 0)
   )
  )
  (i64.store offset=80
   (get_local $0)
   (i64.load offset=64
    (i32.const 0)
   )
  )
  (call $print_i64
   (call $i64sum
    (i32.add
     (get_local $0)
     (i32.const 80)
    )
    (i32.const 5)
   )
  )
  (i32.store
   (i32.add
    (i32.add
     (get_local $0)
     (i32.const 48)
    )
    (i32.const 16)
   )
   (i32.load offset=112
    (i32.const 0)
   )
  )
  (i64.store offset=56
   (get_local $0)
   (i64.load offset=104
    (i32.const 0)
   )
  )
  (i64.store offset=48
   (get_local $0)
   (i64.load offset=96
    (i32.const 0)
   )
  )
  (call $print_f32
   (call $f32sum
    (i32.add
     (get_local $0)
     (i32.const 48)
    )
    (i32.const 5)
   )
  )
  (i64.store offset=16
   (tee_local $0
    (call $memset
     (get_local $0)
     (i32.const 0)
     (i32.const 40)
    )
   )
   (i64.const 4611686018427387904)
  )
  (i64.store offset=8
   (get_local $0)
   (i64.const 4607182418800017408)
  )
  (i64.store offset=24
   (get_local $0)
   (i64.const 4613937818241073152)
  )
  (i64.store offset=32
   (get_local $0)
   (i64.const 4616189618054758400)
  )
  (call $print_f64
   (call $f64sum
    (get_local $0)
    (i32.const 5)
   )
  )
  (i32.store offset=4
   (i32.const 0)
   (i32.add
    (get_local $0)
    (i32.const 144)
   )
  )
  (i32.const 0)
 )
)
