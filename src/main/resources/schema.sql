create table if not exists jbtests.my_arrays
(
    id          serial primary key,
    int_arrays  integer ARRAY,
    str_arrays_one varchar(30) ARRAY,
    str_arrays_two varchar(30) ARRAY,
    dates_array date ARRAY,
    str  varchar(36)
    );