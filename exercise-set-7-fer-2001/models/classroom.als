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

pred show[]{
}
run show
/* Every person is a student. */
pred inv1{
all p: Person | p in Student
}
run inv1

pred inv_1{
	Person in Student
}

assert checkInv1{
	inv1 iff inv_1
}

pred inv_2{
	no Teacher
}

pred inv_3{
	//no Student & Teacher
}

assert checkInv3{
	inv3 iff inv_3
}

check checkInv3


pred inv_4{
	Person in (Student + Teacher)
}


check checkInv1

/* There are no teachers. */
pred inv2{
all p: Person | !(p in Teacher)
}

run inv2

/* No person is both a student and a teacher. */
pred inv3{
	all p: Person | !(p in Teacher and p in Student )
}
run inv3


/* No person is neither a student nor a teacher. */
pred inv4{
all p: Person | ((p in Teacher) and (p in Student)) or !((p in Teacher) and (p in Student))
}
run inv4


/* There are some classes assigned to teachers. */
pred inv5{
some c:Class | some t: Teacher| some g: Group| (t->g) in c.Groups 
}
run inv5

assert inv5Check{
	inv5[] implies all t:Teacher | #t.Teaches = 0
}

check inv5Check

/* Every teacher has classes assigned. */
pred inv6 {
	some c:Class | all t: Teacher| some g: Group| (t->g) in c.Groups 
}

assert inv6Check{
	inv6[] implies all t:Teacher | #t.Teaches = 0
}

check inv6Check

run inv6

/* Every class has teachers assigned. */
pred inv7 {
	all c:Class | some t: Teacher| some g: Group| (t->g) in c.Groups 
}

run inv7

assert inv7Check{
	inv7[] implies all c:Class | #c.Groups = 0
}

check inv7Check for 3 but exactly 3 Class

/* Teachers are assigned at most one class. */
pred inv8 {
	all t: Teacher| lone t.Teaches
}

run inv8

assert inv8Check{
	inv8[] implies all t: Teacher| #t.Teaches>1
}

check inv8Check

/* No class has more than a teacher assigned. */
pred inv9 {
some c:Class | one t:Teacher |some g: Group | (t->g) in c.Groups
}

run inv9
assert inv9Check{
	inv9[] implies #Teaches >0 and some c:Class , t,t1:Teacher
	 | c in t.Teaches and c in t1.Teaches
}

check inv9Check

/* For every class, every student has a group assigned. */
pred inv10 {
    all c:Class| all s:Student| some g:Group| (s->g) in c.Groups	
}

run inv10

pred inv_10{
	 all c:Class| all s:Student| some s.(c.Groups)
}

assert checkInv10{
	inv10 iff inv_10
}

check checkInv10


/* A class only has groups if it has a teacher assigned. */
pred inv11 {
	all c:Class| some t:Teacher| c in t.Teaches implies #c.Groups>0
}

run inv11


/* Each teacher is responsible for some groups. */
pred inv12 {

}

run inv12


pred inv_12{

all t:Teacher | some (t.Teaches).Groups
}

run inv_12

assert checkInv12{
	inv12 iff inv_12
}

check checkInv12

/* Only teachers tutor, and only students are tutored. */
pred inv13 {
//	all p: Person | all t.Teacher| p.Teaches = 
}

run inv13


pred inv_13{
	Tutors.Person in Teacher and Person.Tutors in Student
}

run inv_13

assert checkInv13{
	inv13 iff inv_13
}

check checkInv13


/* Every student in a class is at least tutored by all the teachers
 * assigned to that class. */
pred inv14 {
    // TODO: Specify this property	
}


/* The tutoring chain of every person eventually reaches a Teacher. */
pred inv15 {
    // TODO: Specify this property	
}
