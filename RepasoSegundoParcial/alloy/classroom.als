/* The registered persons. */
sig Person  {
	/* Each person tutors a set of persons. */
	Tutors : set Person,
	/* Each person teaches a set of classes. */
	Teaches : set Class
}
/* The registered groups. */
sig Group {}

/* The registered classes. */
sig Class  {
	/* Each class has a set of persons assigned to a group. */
	Groups : Person -> Group
}

/* Some persons are teachers. */
sig Teacher extends Person  {}

/* Some persons are students. */
sig Student extends Person  {}

fact onlyTeacherTeaches{
	//
}

/* Every person is a student. */
pred inv1 {
	all  p:Person | p in Student 
}

/* There are no teachers. */
pred inv2 {
    all p:Person| !p in Teacher	
}

/* No person is both a student and a teacher. */
pred inv3 {
    	all p:Person | !(p in Teacher and p in Student)
}

/* No person is neither a student nor a teacher. */
pred inv4 {
   	 all p:Person | p in Teacher and !p in Student or
	 (!p in Teacher and p in Student )
}

/* There are some classes assigned to teachers. */ 
pred inv5 {
   	some c:Class ,  t:Teacher| c in t.Teaches 
  	//some  p:Person| lone p.Teaches 	
}

assert inv5Check{
	inv5[] implies all t:Teacher | #t.Teaches = 0
}

/* Every teacher has classes assigned. */
pred inv6 {
   all t:Teacher | #t.Teaches > 0	
}

assert inv6Check{
	inv6[] iff inv6_0[]
}

/* Every class has teachers assigned. */
pred inv7 {
    	//all c:Class ,  t:Teacher | c in t.Teaches
	all c:Class | some t:Teacher | c in t.Teaches	
}
assert inv7Check{
	inv7[] iff inv7_0[]
}

/* Teachers are assigned at most one class. */
pred inv8 {
    	all t:Teacher | lone t.Teaches 
}
assert inv8Check{
	inv8[] iff inv8_0[]
}

/* No class has more than a teacher assigned. ???? como funciona esto*/
pred inv9 {
	all c:Class | lone Teaches.c & Teacher
	// 
}
assert inv9Check{
	inv9[] iff inv9_0[]
}

/*assert inv9Check{
	inv9[] implies #Teaches >0 and some c:Class , t,t1:Teacher
	 | c in t.Teaches and c in t1.Teaches
}*/
/* For every class, every student has a group assigned. */
pred inv10 {
    all c:Class, s:Student|some g:Group | (c->s->g) in Groups and #c.Groups > 0
}
assert inv10Check{
	inv10[] implies inv10_0[]
}

/* A class only has groups if it has a teacher assigned. ??????????????????????????????????????????*/
pred inv11 {
   	all c:Class, t:Teacher, p:Person| (#c.Groups > 0 ) and #Tutors > 0 implies  c in t.Teaches  and #Tutors > 0 else 
	 #c.Groups = 0   implies ! c in p.Teaches 
}
assert inv11Check{
	inv11[] iff inv11_0[]
}

/* Each teacher is responsible for some groups. ????*/
pred inv12 {
   	// all t:Teacher, c:Class, p:Person|some g:Group| (p->g) in (c.Groups)
	 //implies p in t.Tutors
	all t:Teacher | some (t.Teaches).Groups
}
assert inv12Check{
	inv12[] iff inv12_0[]
}

/* Only teachers tutor, and only students are tutored. como leo esto? */
pred inv13 {
	all p:Person, t:Teacher, s:Student |inv4[] and (t->s) in Tutors and ! t in p.Tutors 
	and s in p.Tutors and !(s->p) in Tutors
	
}
assert inv13Check{
	inv13[] iff inv13_0[]
}

/*
assert inv13Check{
	all p:Person, t:Teacher, s:Student |inv13[] implies !(t->s) in Tutors or t in p.Tutors
	or ! s in p.Tutors or (t->s) in Tutors
}*/

/* Every student in a class is at least tutored by all the teachers ????????????
 * assigned to that class. */
pred inv14 {
 	   --all s:Student , t:Teacher|some c:Class, g:Group| c in t.Teaches and 
	--(s->g) in c.Groups implies s in t.Tutors
	all s:Person, c:Class, t:Person, g:Group | (c->s->g in Groups) and
	t->c in Teaches implies t->s in Tutors
}
assert inv14Check{
	inv14[] iff inv14_0[]
}

/*
assert inv14Check{
	all s:Student , t:Teacher| inv14[] implies !s in t.Tutors
}*/


/* The tutoring chain of every person eventually reaches a Teacher. no lo entendi jeje */
pred inv15 {
   	--all p:Person | p.*Tutors in Teacher 
	all s:Person | some Teacher & *Tutors.s
}
assert inv15Check{
	inv15[] iff inv15_0[]
}

//HECHO POR SONIA
pred inv6_0{
	Teacher in Teaches.Class
}

pred inv7_0{
	Class in Teacher.Teaches
}

pred inv8_0{
	all t:Teacher | lone t.Teaches
}

pred inv9_0{
	all c:Class | lone Teaches.c & Teacher
}

pred inv10_0{
	all c:Class, s:Student | some s.(c.Groups)
}

pred inv11_0{
	all c:Class | (some c.Groups) implies some Teacher & Teaches.c
}

pred inv12_0{
	all t:Teacher | some (t.Teaches).Groups
}

pred inv13_0{
	Tutors.Person in Teacher and Person.Tutors in Student
}

pred inv14_0{
	all s:Person, c:Class, t:Person, g:Group | (c->s->g in Groups) and
	t->c in Teaches implies t->s in Tutors
}

pred inv15_0{
	all s:Person | some Teacher & *Tutors.s
}


pred show []{}
run show
run inv1 for 3 but exactly 5 Person // all students
run inv2 for 3 but exactly 5 Person //no teachers
run inv3 for 3 but exactly 3 Person, 0 Group, 0 Class /* No person is both a student and a teacher. */
run inv4 for 3 but exactly 8 Person, 0 Group, 0 Class /* No person is neither a student nor a teacher. */
run inv5 for 3 but exactly 3 Person, 0 Group, 3 Class
check inv5Check 
run inv6 for 3 but exactly 3 Person, 0 Group, 3 Class
check inv6Check //BIEN
run inv7 for 3 but exactly 2 Person, 0 Group, 3 Class
check inv7Check //BIEN
run inv8 for 3 but exactly 3 Person, 0 Group, 3 Class //Teacher at most have 1 class
check inv8Check //BIEN
run inv9 for 3 but exactly 8 Person, 0 Group //Class has at most 1 teacher
check inv9Check
run inv10 for 3 but exactly 2 Group, 4 Person
check inv10Check 
run inv11 for 3 but exactly 2 Group, 5 Person
check inv11Check  for 3 
run inv12 
check inv12Check 
run inv13 for 3 but exactly 5 Person, 1 Class, 1 Group
check inv13Check
run inv14
check inv14Check 
run inv15 for 3 but exactly 0 Class, 0 Group, 6 Person
check inv15Check 
