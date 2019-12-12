(ns aoc-2019.inputs)

(def input-day1
  [89407
   103327
   75227
   80462
   147732
   127392
   147052
   67987
   69650
   63139
   117260
   75686
   146517
   147057
   91654
   96757
   123428
   118351
   84167
   73536
   59261
   139879
   85969
   93931
   125232
   62629
   107163
   105032
   124295
   112716
   72402
   137719
   126924
   59903
   102568
   63963
   145435
   54578
   141348
   77099
   64050
   60012
   131514
   81400
   118451
   124420
   124821
   51746
   72382
   125018
   130662
   116926
   73573
   117827
   101462
   85172
   123277
   62842
   91856
   61046
   57290
   86265
   59080
   55713
   88492
   138409
   134009
   114376
   86621
   107651
   146528
   135273
   87760
   134164
   141430
   133574
   109457
   110225
   147989
   74089
   55747
   61602
   139444
   111397
   95751
   133049
   129641
   101287
   88916
   83340
   140286
   88824
   66013
   65935
   141174
   105662
   97399
   91345
   120164
   80904
   ])


(def input-day2
  [1 0 0 3
   1 1 2 3
   1 3 4 3
   1 5 0 3
   2 13 1 19
   1 6 19 23
   2 6 23 27
   1 5 27 31
   2 31 9 35
   1 35 5 39
   1 39 5 43
   1 43 10 47
   2 6 47 51
   1 51 5 55
   2 55 6 59
   1 5 59 63
   2 63 6 67
   1 5 67 71
   1 71 6 75
   2 75 10 79
   1 79 5 83
   2 83 6 87
   1 87 5 91
   2 9 91 95
   1 95 6 99
   2 9 99 103
   2 9 103 107
   1 5 107 111
   1 111 5 115
   1 115 13 119
   1 13 119 123
   2 6 123 127
   1 5 127 131
   1 9 131 135
   1 135 9 139
   2 139 6 143
   1 143 5 147
   2 147 6 151
   1 5 151 155
   2 6 155 159
   1 159 2 163
   1 9 163 0
   99
   2 0 14 0])


(def day3-input-test0
  {:first
   [{ :direction :R, :length 8 },
    { :direction :U, :length 5 },
    { :direction :L, :length 5 },
    { :direction :D, :length 3 }]
   :second
   [{ :direction :U, :length 7 },
    { :direction :R, :length 6 },
    { :direction :D, :length 4 },
    { :direction :L, :length 4 }]})

(def day3-input-test1
  {:first
   [{ :direction :R, :length 75},
    { :direction :D, :length 30},
    { :direction :R, :length 83},
    { :direction :U, :length 83},
    { :direction :L, :length 12},
    { :direction :D, :length 49},
    { :direction :R, :length 71},
    { :direction :U, :length 7 },
    { :direction :L, :length 72 }],
   :second
   [{ :direction :U, :length 62 },
    { :direction :R, :length 66 },
    { :direction :U, :length 55 },
    { :direction :R, :length 34 },
    { :direction :D, :length 71 },
    { :direction :R, :length 55 },
    { :direction :D, :length 58 },
    { :direction :R, :length 83 }]})

(def day3-input-test2
  {:first
   [{ :direction :R, :length 98 },
    { :direction :U, :length 47 },
    { :direction :R, :length 26 },
    { :direction :D, :length 63 },
    { :direction :R, :length 33 },
    { :direction :U, :length 87 },
    { :direction :L, :length 62 },
    { :direction :D, :length 20 },
    { :direction :R, :length 33 },
    { :direction :U, :length 53 },
    { :direction :R, :length 51 }],
   :second
   [{ :direction :U, :length 98 },
    { :direction :R, :length 91 },
    { :direction :D, :length 20 },
    { :direction :R, :length 16 },
    { :direction :D, :length 67 },
    { :direction :R, :length 40 },
    { :direction :U, :length 7 },
    { :direction :R, :length 15 },
    { :direction :U, :length 6 },
    { :direction :R, :length 7 }]})

(def day3-input-test3
  { :first
   [{ :direction :R, :length 75 },
    { :direction :D, :length 30 },
    { :direction :R, :length 83 },
    { :direction :U, :length 83 },
    { :direction :L, :length 12 },
    { :direction :D, :length 49 },
    { :direction :R, :length 71 },
    { :direction :U, :length 7 },
    { :direction :L, :length 72 }]
   :second
   [{ :direction :U, :length 62 },
    { :direction :R, :length 66 },
    { :direction :U, :length 55 },
    { :direction :R, :length 34 },
    { :direction :D, :length 71 },
    { :direction :R, :length 55 },
    { :direction :D, :length 58 },
    { :direction :R, :length 83 }]})
;; = 610 steps
(def day3-input-test4
  {:first
   [{ :direction :R, :length 98 },
    { :direction :U, :length 47 },
    { :direction :R, :length 26 },
    { :direction :D, :length 63 },
    { :direction :R, :length 33 },
    { :direction :U, :length 87 },
    { :direction :L, :length 62 },
    { :direction :D, :length 20 },
    { :direction :R, :length 33 },
    { :direction :U, :length 53 },
    { :direction :R, :length 51 }]
   :second
   [{ :direction :U, :length 98 },
    { :direction :R, :length 91 },
    { :direction :D, :length 20 },
    { :direction :R, :length 16 },
    { :direction :D, :length 67 },
    { :direction :R, :length 40 },
    { :direction :U, :length 7 },
    { :direction :R, :length 15 },
    { :direction :U, :length 6 },
    { :direction :R, :length 7 }]})
;; = 410 steps


(def day3-input
  {:first
   [{ :direction :R, :length 995 },
    { :direction :D, :length 933 },
    { :direction :L, :length 284 },
    { :direction :U, :length 580 },
    { :direction :R, :length 453 },
    { :direction :U, :length 355 },
    { :direction :L, :length 352 },
    { :direction :U, :length 363 },
    { :direction :L, :length 506 },
    { :direction :D, :length 130 },
    { :direction :R, :length 300 },
    { :direction :D, :length 112 },
    { :direction :L, :length 751 },
    { :direction :U, :length 245 },
    { :direction :R, :length 174 },
    { :direction :U, :length 901 },
    { :direction :L, :length 586 },
    { :direction :D, :length 70 },
    { :direction :L, :length 348 },
    { :direction :U, :length 307 },
    { :direction :R, :length 596 },
    { :direction :D, :length 401 },
    { :direction :R, :length 311 },
    { :direction :D, :length 328 },
    { :direction :L, :length 612 },
    { :direction :D, :length 214 },
    { :direction :L, :length 161 },
    { :direction :U, :length 488 },
    { :direction :L, :length 813 },
    { :direction :U, :length 298 },
    { :direction :L, :length 299 },
    { :direction :D, :length 807 },
    { :direction :L, :length 791 },
    { :direction :D, :length 813 },
    { :direction :R, :length 946 },
    { :direction :U, :length 958 },
    { :direction :R, :length 258 },
    { :direction :D, :length 972 },
    { :direction :L, :length 72 },
    { :direction :U, :length 698 },
    { :direction :L, :length 170 },
    { :direction :D, :length 131 },
    { :direction :L, :length 552 },
    { :direction :D, :length 14 },
    { :direction :L, :length 760 },
    { :direction :U, :length 812 },
    { :direction :L, :length 203 },
    { :direction :D, :length 92 },
    { :direction :R, :length 483 },
    { :direction :U, :length 698 },
    { :direction :R, :length 800 },
    { :direction :U, :length 345 },
    { :direction :L, :length 947 },
    { :direction :D, :length 206 },
    { :direction :L, :length 991 },
    { :direction :D, :length 447 },
    { :direction :R, :length 832 },
    { :direction :D, :length 52 },
    { :direction :L, :length 134 },
    { :direction :D, :length 946 },
    { :direction :R, :length 108 },
    { :direction :D, :length 477 },
    { :direction :L, :length 818 },
    { :direction :D, :length 101 },
    { :direction :R, :length 784 },
    { :direction :U, :length 409 },
    { :direction :R, :length 23 },
    { :direction :U, :length 359 },
    { :direction :R, :length 981 },
    { :direction :D, :length 458 },
    { :direction :R, :length 786 },
    { :direction :U, :length 445 },
    { :direction :R, :length 874 },
    { :direction :U, :length 244 },
    { :direction :R, :length 381 },
    { :direction :U, :length 206 },
    { :direction :R, :length 86 },
    { :direction :U, :length 279 },
    { :direction :L, :length 787 },
    { :direction :U, :length 683 },
    { :direction :R, :length 52 },
    { :direction :U, :length 740 },
    { :direction :R, :length 210 },
    { :direction :U, :length 162 },
    { :direction :L, :length 748 },
    { :direction :U, :length 747 },
    { :direction :R, :length 283 },
    { :direction :D, :length 964 },
    { :direction :R, :length 782 },
    { :direction :D, :length 386 },
    { :direction :R, :length 205 },
    { :direction :D, :length 898 },
    { :direction :R, :length 774 },
    { :direction :U, :length 421 },
    { :direction :R, :length 908 },
    { :direction :D, :length 280 },
    { :direction :L, :length 567 },
    { :direction :D, :length 667 },
    { :direction :L, :length 302 },
    { :direction :D, :length 795 },
    { :direction :L, :length 822 },
    { :direction :D, :length 908 },
    { :direction :L, :length 160 },
    { :direction :U, :length 279 },
    { :direction :L, :length 58 },
    { :direction :D, :length 914 },
    { :direction :R, :length 828 },
    { :direction :U, :length 621 },
    { :direction :R, :length 338 },
    { :direction :U, :length 982 },
    { :direction :R, :length 471 },
    { :direction :U, :length 724 },
    { :direction :L, :length 509 },
    { :direction :U, :length 444 },
    { :direction :R, :length 377 },
    { :direction :D, :length 473 },
    { :direction :R, :length 307 },
    { :direction :U, :length 331 },
    { :direction :L, :length 529 },
    { :direction :U, :length 855 },
    { :direction :L, :length 474 },
    { :direction :U, :length 725 },
    { :direction :L, :length 905 },
    { :direction :U, :length 409 },
    { :direction :L, :length 881 },
    { :direction :U, :length 702 },
    { :direction :R, :length 162 },
    { :direction :D, :length 717 },
    { :direction :R, :length 498 },
    { :direction :U, :length 458 },
    { :direction :R, :length 119 },
    { :direction :U, :length 744 },
    { :direction :R, :length 2 },
    { :direction :D, :length 82 },
    { :direction :R, :length 337 },
    { :direction :D, :length 988 },
    { :direction :L, :length 235 },
    { :direction :U, :length 831 },
    { :direction :L, :length 373 },
    { :direction :D, :length 122 },
    { :direction :L, :length 310 },
    { :direction :D, :length 107 },
    { :direction :R, :length 685 },
    { :direction :U, :length 864 },
    { :direction :L, :length 356 },
    { :direction :U, :length 213 },
    { :direction :R, :length 246 },
    { :direction :U, :length 373 },
    { :direction :L, :length 809 },
    { :direction :U, :length 593 },
    { :direction :R, :length 582 },
    { :direction :U, :length 488 },
    { :direction :R, :length 847 },
    { :direction :U, :length 792 },
    { :direction :L, :length 182 },
    { :direction :U, :length 219 },
    { :direction :L, :length 232 },
    { :direction :D, :length 695 },
    { :direction :R, :length 183 },
    { :direction :U, :length 164 },
    { :direction :L, :length 730 },
    { :direction :D, :length 660 },
    { :direction :L, :length 581 },
    { :direction :D, :length 931 },
    { :direction :R, :length 36 },
    { :direction :D, :length 291 },
    { :direction :R, :length 614 },
    { :direction :U, :length 408 },
    { :direction :R, :length 928 },
    { :direction :D, :length 93 },
    { :direction :L, :length 685 },
    { :direction :D, :length 879 },
    { :direction :R, :length 37 },
    { :direction :D, :length 113 },
    { :direction :L, :length 563 },
    { :direction :D, :length 340 },
    { :direction :L, :length 212 },
    { :direction :D, :length 907 },
    { :direction :L, :length 557 },
    { :direction :D, :length 522 },
    { :direction :L, :length 610 },
    { :direction :D, :length 927 },
    { :direction :R, :length 11 },
    { :direction :U, :length 556 },
    { :direction :R, :length 571 },
    { :direction :U, :length 668 },
    { :direction :L, :length 834 },
    { :direction :U, :length 603 },
    { :direction :L, :length 407 },
    { :direction :U, :length 966 },
    { :direction :R, :length 245 },
    { :direction :D, :length 408 },
    { :direction :R, :length 822 },
    { :direction :U, :length 163 },
    { :direction :L, :length 265 },
    { :direction :D, :length 230 },
    { :direction :L, :length 144 },
    { :direction :D, :length 786 },
    { :direction :R, :length 479 },
    { :direction :U, :length 208 },
    { :direction :L, :length 781 },
    { :direction :D, :length 787 },
    { :direction :L, :length 146 },
    { :direction :U, :length 476 },
    { :direction :R, :length 561 },
    { :direction :U, :length 70 },
    { :direction :R, :length 879 },
    { :direction :U, :length 562 },
    { :direction :R, :length 805 },
    { :direction :D, :length 897 },
    { :direction :L, :length 44 },
    { :direction :U, :length 709 },
    { :direction :L, :length 773 },
    { :direction :U, :length 747 },
    { :direction :L, :length 806 },
    { :direction :U, :length 140 },
    { :direction :R, :length 953 },
    { :direction :D, :length 628 },
    { :direction :L, :length 752 },
    { :direction :D, :length 666 },
    { :direction :R, :length 916 },
    { :direction :D, :length 351 },
    { :direction :R, :length 175 },
    { :direction :D, :length 504 },
    { :direction :R, :length 232 },
    { :direction :U, :length 463 },
    { :direction :R, :length 415 },
    { :direction :U, :length 492 },
    { :direction :L, :length 252 },
    { :direction :D, :length 30 },
    { :direction :L, :length 574 },
    { :direction :U, :length 100 },
    { :direction :L, :length 333 },
    { :direction :U, :length 313 },
    { :direction :R, :length 403 },
    { :direction :D, :length 68 },
    { :direction :L, :length 976 },
    { :direction :D, :length 702 },
    { :direction :L, :length 205 },
    { :direction :D, :length 992 },
    { :direction :L, :length 552 },
    { :direction :U, :length 55 },
    { :direction :R, :length 216 },
    { :direction :U, :length 548 },
    { :direction :L, :length 894 },
    { :direction :U, :length 764 },
    { :direction :L, :length 919 },
    { :direction :U, :length 475 },
    { :direction :R, :length 656 },
    { :direction :U, :length 712 },
    { :direction :L, :length 754 },
    { :direction :U, :length 638 },
    { :direction :R, :length 33 },
    { :direction :U, :length 709 },
    { :direction :R, :length 643 },
    { :direction :U, :length 299 },
    { :direction :R, :length 151 },
    { :direction :U, :length 762 },
    { :direction :R, :length 767 },
    { :direction :D, :length 542 },
    { :direction :R, :length 581 },
    { :direction :D, :length 752 },
    { :direction :L, :length 701 },
    { :direction :D, :length 589 },
    { :direction :L, :length 312 },
    { :direction :U, :length 189 },
    { :direction :R, :length 922 },
    { :direction :D, :length 915 },
    { :direction :R, :length 975 },
    { :direction :U, :length 26 },
    { :direction :R, :length 244 },
    { :direction :U, :length 48 },
    { :direction :L, :length 971 },
    { :direction :U, :length 755 },
    { :direction :R, :length 889 },
    { :direction :D, :length 726 },
    { :direction :R, :length 126 },
    { :direction :U, :length 978 },
    { :direction :L, :length 275 },
    { :direction :D, :length 435 },
    { :direction :L, :length 732 },
    { :direction :D, :length 982 },
    { :direction :L, :length 654 },
    { :direction :D, :length 704 },
    { :direction :L, :length 98 },
    { :direction :U, :length 153 },
    { :direction :L, :length 983 },
    { :direction :U, :length 770 },
    { :direction :L, :length 429 },
    { :direction :U, :length 530 },
    { :direction :L, :length 545 },
    { :direction :D, :length 44 },
    { :direction :L, :length 36 },
    { :direction :U, :length 456 },
    { :direction :R, :length 442 },
    { :direction :D, :length 58 },
    { :direction :L, :length 321 },
    { :direction :U, :length 473 },
    { :direction :R, :length 657 },
    { :direction :U, :length 307 },
    { :direction :R, :length 314 }],
   :second
   [{ :direction :L, :length 999 },
    { :direction :U, :length 880 },
    { :direction :L, :length 754 },
    { :direction :D, :length 995 },
    { :direction :R, :length 105 },
    { :direction :U, :length 651 },
    { :direction :R, :length 762 },
    { :direction :U, :length 451 },
    { :direction :R, :length 612 },
    { :direction :U, :length 424 },
    { :direction :L, :length 216 },
    { :direction :D, :length 210 },
    { :direction :L, :length 946 },
    { :direction :U, :length 57 },
    { :direction :R, :length 685 },
    { :direction :U, :length 185 },
    { :direction :R, :length 234 },
    { :direction :D, :length 768 },
    { :direction :L, :length 927 },
    { :direction :U, :length 592 },
    { :direction :R, :length 545 },
    { :direction :U, :length 770 },
    { :direction :R, :length 456 },
    { :direction :D, :length 118 },
    { :direction :R, :length 22 },
    { :direction :U, :length 371 },
    { :direction :L, :length 721 },
    { :direction :D, :length 937 },
    { :direction :R, :length 144 },
    { :direction :U, :length 173 },
    { :direction :R, :length 337 },
    { :direction :D, :length 17 },
    { :direction :L, :length 948 },
    { :direction :U, :length 720 },
    { :direction :R, :length 617 },
    { :direction :D, :length 588 },
    { :direction :L, :length 57 },
    { :direction :U, :length 258 },
    { :direction :L, :length 979 },
    { :direction :U, :length 232 },
    { :direction :L, :length 473 },
    { :direction :D, :length 451 },
    { :direction :L, :length 829 },
    { :direction :D, :length 85 },
    { :direction :L, :length 985 },
    { :direction :D, :length 333 },
    { :direction :L, :length 492 },
    { :direction :D, :length 749 },
    { :direction :L, :length 972 },
    { :direction :U, :length 188 },
    { :direction :R, :length 214 },
    { :direction :D, :length 108 },
    { :direction :R, :length 247 },
    { :direction :U, :length 379 },
    { :direction :L, :length 218 },
    { :direction :D, :length 490 },
    { :direction :R, :length 451 },
    { :direction :U, :length 582 },
    { :direction :R, :length 674 },
    { :direction :D, :length 881 },
    { :direction :R, :length 725 },
    { :direction :U, :length 404 },
    { :direction :L, :length 916 },
    { :direction :U, :length 137 },
    { :direction :R, :length 745 },
    { :direction :D, :length 969 },
    { :direction :L, :length 206 },
    { :direction :D, :length 480 },
    { :direction :R, :length 634 },
    { :direction :U, :length 672 },
    { :direction :R, :length 897 },
    { :direction :D, :length 184 },
    { :direction :L, :length 768 },
    { :direction :D, :length 766 },
    { :direction :L, :length 529 },
    { :direction :U, :length 317 },
    { :direction :L, :length 909 },
    { :direction :D, :length 74 },
    { :direction :R, :length 529 },
    { :direction :D, :length 31 },
    { :direction :R, :length 483 },
    { :direction :D, :length 906 },
    { :direction :R, :length 961 },
    { :direction :D, :length 535 },
    { :direction :L, :length 937 },
    { :direction :D, :length 751 },
    { :direction :L, :length 564 },
    { :direction :U, :length 478 },
    { :direction :L, :length 439 },
    { :direction :U, :length 556 },
    { :direction :R, :length 385 },
    { :direction :D, :length 590 },
    { :direction :L, :length 518 },
    { :direction :D, :length 991 },
    { :direction :L, :length 232 },
    { :direction :D, :length 358 },
    { :direction :L, :length 962 },
    { :direction :U, :length 242 },
    { :direction :R, :length 856 },
    { :direction :U, :length 66 },
    { :direction :L, :length 847 },
    { :direction :U, :length 146 },
    { :direction :R, :length 196 },
    { :direction :U, :length 515 },
    { :direction :L, :length 892 },
    { :direction :U, :length 18 },
    { :direction :L, :length 535 },
    { :direction :U, :length 343 },
    { :direction :R, :length 430 },
    { :direction :U, :length 77 },
    { :direction :L, :length 967 },
    { :direction :U, :length 956 },
    { :direction :L, :length 64 },
    { :direction :D, :length 510 },
    { :direction :L, :length 29 },
    { :direction :D, :length 305 },
    { :direction :L, :length 954 },
    { :direction :U, :length 186 },
    { :direction :R, :length 624 },
    { :direction :D, :length 693 },
    { :direction :R, :length 354 },
    { :direction :D, :length 243 },
    { :direction :L, :length 145 },
    { :direction :D, :length 622 },
    { :direction :R, :length 456 },
    { :direction :U, :length 904 },
    { :direction :L, :length 233 },
    { :direction :D, :length 940 },
    { :direction :L, :length 82 },
    { :direction :D, :length 83 },
    { :direction :L, :length 726 },
    { :direction :D, :length 993 },
    { :direction :L, :length 338 },
    { :direction :D, :length 793 },
    { :direction :R, :length 340 },
    { :direction :D, :length 823 },
    { :direction :R, :length 147 },
    { :direction :D, :length 595 },
    { :direction :R, :length 296 },
    { :direction :D, :length 388 },
    { :direction :L, :length 106 },
    { :direction :D, :length 362 },
    { :direction :R, :length 114 },
    { :direction :U, :length 808 },
    { :direction :L, :length 496 },
    { :direction :U, :length 614 },
    { :direction :L, :length 809 },
    { :direction :U, :length 911 },
    { :direction :R, :length 480 },
    { :direction :D, :length 29 },
    { :direction :L, :length 802 },
    { :direction :U, :length 900 },
    { :direction :R, :length 920 },
    { :direction :U, :length 952 },
    { :direction :R, :length 237 },
    { :direction :D, :length 383 },
    { :direction :L, :length 480 },
    { :direction :U, :length 362 },
    { :direction :L, :length 761 },
    { :direction :U, :length 949 },
    { :direction :L, :length 920 },
    { :direction :D, :length 82 },
    { :direction :L, :length 511 },
    { :direction :U, :length 365 },
    { :direction :R, :length 657 },
    { :direction :U, :length 465 },
    { :direction :L, :length 256 },
    { :direction :U, :length 124 },
    { :direction :L, :length 810 },
    { :direction :U, :length 43 },
    { :direction :L, :length 668 },
    { :direction :U, :length 146 },
    { :direction :L, :length 847 },
    { :direction :D, :length 245 },
    { :direction :R, :length 937 },
    { :direction :D, :length 778 },
    { :direction :L, :length 995 },
    { :direction :D, :length 316 },
    { :direction :R, :length 423 },
    { :direction :U, :length 515 },
    { :direction :L, :length 626 },
    { :direction :D, :length 788 },
    { :direction :R, :length 453 },
    { :direction :U, :length 98 },
    { :direction :R, :length 886 },
    { :direction :U, :length 821 },
    { :direction :R, :length 749 },
    { :direction :D, :length 365 },
    { :direction :R, :length 915 },
    { :direction :U, :length 980 },
    { :direction :R, :length 322 },
    { :direction :D, :length 114 },
    { :direction :L, :length 556 },
    { :direction :D, :length 921 },
    { :direction :L, :length 551 },
    { :direction :D, :length 397 },
    { :direction :R, :length 232 },
    { :direction :D, :length 485 },
    { :direction :L, :length 842 },
    { :direction :D, :length 455 },
    { :direction :R, :length 940 },
    { :direction :U, :length 913 },
    { :direction :L, :length 196 },
    { :direction :D, :length 239 },
    { :direction :L, :length 221 },
    { :direction :D, :length 200 },
    { :direction :R, :length 438 },
    { :direction :U, :length 71 },
    { :direction :L, :length 979 },
    { :direction :U, :length 527 },
    { :direction :L, :length 86 },
    { :direction :U, :length 959 },
    { :direction :R, :length 768 },
    { :direction :D, :length 557 },
    { :direction :R, :length 553 },
    { :direction :D, :length 709 },
    { :direction :L, :length 838 },
    { :direction :U, :length 191 },
    { :direction :L, :length 916 },
    { :direction :D, :length 703 },
    { :direction :L, :length 687 },
    { :direction :U, :length 34 },
    { :direction :R, :length 463 },
    { :direction :D, :length 809 },
    { :direction :L, :length 3 },
    { :direction :U, :length 335 },
    { :direction :L, :length 231 },
    { :direction :D, :length 212 },
    { :direction :L, :length 674 },
    { :direction :U, :length 177 },
    { :direction :L, :length 51 },
    { :direction :D, :length 557 },
    { :direction :L, :length 939 },
    { :direction :U, :length 390 },
    { :direction :L, :length 28 },
    { :direction :U, :length 53 },
    { :direction :L, :length 653 },
    { :direction :D, :length 182 },
    { :direction :R, :length 329 },
    { :direction :D, :length 449 },
    { :direction :L, :length 563 },
    { :direction :D, :length 476 },
    { :direction :R, :length 258 },
    { :direction :D, :length 299 },
    { :direction :L, :length 142 },
    { :direction :U, :length 847 },
    { :direction :L, :length 38 },
    { :direction :U, :length 322 },
    { :direction :R, :length 294 },
    { :direction :U, :length 320 },
    { :direction :R, :length 314 },
    { :direction :D, :length 257 },
    { :direction :R, :length 622 },
    { :direction :U, :length 59 },
    { :direction :R, :length 501 },
    { :direction :D, :length 677 },
    { :direction :L, :length 880 },
    { :direction :U, :length 589 },
    { :direction :R, :length 599 },
    { :direction :D, :length 245 },
    { :direction :L, :length 851 },
    { :direction :U, :length 369 },
    { :direction :R, :length 262 },
    { :direction :D, :length 63 },
    { :direction :R, :length 722 },
    { :direction :D, :length 253 },
    { :direction :L, :length 546 },
    { :direction :U, :length 578 },
    { :direction :R, :length 909 },
    { :direction :U, :length 678 },
    { :direction :L, :length 63 },
    { :direction :U, :length 256 },
    { :direction :L, :length 237 },
    { :direction :U, :length 798 },
    { :direction :R, :length 465 },
    { :direction :D, :length 420 },
    { :direction :R, :length 797 },
    { :direction :D, :length 452 },
    { :direction :R, :length 548 },
    { :direction :U, :length 875 },
    { :direction :R, :length 523 },
    { :direction :D, :length 527 },
    { :direction :L, :length 467 },
    { :direction :U, :length 49 },
    { :direction :R, :length 726 },
    { :direction :D, :length 840 },
    { :direction :R, :length 882 },
    { :direction :U, :length 97 },
    { :direction :L, :length 398 },
    { :direction :D, :length 621 },
    { :direction :R, :length 38 },
    { :direction :U, :length 952 },
    { :direction :R, :length 196 },
    { :direction :D, :length 597 },
    { :direction :R, :length 627 },
    { :direction :D, :length 721 },
    { :direction :L, :length 441 },
    { :direction :D, :length 710 },
    { :direction :L, :length 18 },
    { :direction :D, :length 679 },
    { :direction :R, :length 218 }]})

(def day5-input
  [3,225,
   1,225,6,6,
   1100,1,238,225,
   104,0,
   1101,91,67,225,
   1102,67,36,225,
   1102,21,90,225,
   2,13,48,224,
   101,-819,224,224,
   4,224,
   1002,223,8,223,
   101,7,224,224,
   1,223,224,223,
   1101,62,9,225,
   1,139,22,224,
   101,-166,224,224,
   4,224,
   1002,223,8,223,
   101,3,224,224,
   1,223,224,223,
   102,41,195,224,
   101,-2870,224,224,
   4,224,
   1002,223,8,223,
   101,1,224,224,
   1,224,223,223,
   1101,46,60,224,
   101,-106,224,224,
   4,224,
   1002,223,8,223,
   1001,224,2,224,
   1,224,223,223,
   1001,191,32,224,
   101,-87,224,224,
   4,224,
   102,8,223,223,
   1001,224,1,224,
   1,223,224,223,
   1101,76,90,225,
   1101,15,58,225,
   1102,45,42,224,
   101,-1890,224,224,
   4,224,
   1002,223,8,223,
   1001,224,5,224,
   1,224,223,223,101,62,143,224,101,-77,224,224,4,224,1002,223,8,223,1001,224,4,224,1,224,223,223,1101,55,54,225,1102,70,58,225,1002,17,80,224,101,-5360,224,224,4,224,102,8,223,223,1001,224,3,224,1,223,224,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1008,677,677,224,102,2,223,223,1005,224,329,1001,223,1,223,1108,677,226,224,1002,223,2,223,1006,224,344,101,1,223,223,107,677,226,224,1002,223,2,223,1006,224,359,101,1,223,223,108,677,677,224,1002,223,2,223,1006,224,374,1001,223,1,223,108,226,677,224,1002,223,2,223,1006,224,389,101,1,223,223,7,226,677,224,102,2,223,223,1006,224,404,1001,223,1,223,1108,677,677,224,1002,223,2,223,1005,224,419,101,1,223,223,1008,226,677,224,102,2,223,223,1006,224,434,101,1,223,223,107,226,226,224,102,2,223,223,1005,224,449,1001,223,1,223,1007,677,677,224,1002,223,2,223,1006,224,464,1001,223,1,223,1007,226,226,224,1002,223,2,223,1005,224,479,101,1,223,223,1008,226,226,224,102,2,223,223,1006,224,494,1001,223,1,223,8,226,226,224,102,2,223,223,1006,224,509,101,1,223,223,1107,677,677,224,102,2,223,223,1005,224,524,1001,223,1,223,1108,226,677,224,1002,223,2,223,1006,224,539,101,1,223,223,1107,677,226,224,1002,223,2,223,1006,224,554,101,1,223,223,1007,677,226,224,1002,223,2,223,1005,224,569,101,1,223,223,7,677,226,224,1002,223,2,223,1006,224,584,101,1,223,223,107,677,677,224,1002,223,2,223,1005,224,599,1001,223,1,223,8,226,677,224,1002,223,2,223,1005,224,614,101,1,223,223,7,677,677,224,1002,223,2,223,1006,224,629,1001,223,1,223,1107,226,677,224,1002,223,2,223,1006,224,644,101,1,223,223,108,226,226,224,102,2,223,223,1005,224,659,1001,223,1,223,8,677,226,224,1002,223,2,223,1005,224,674,101,1,223,223,4,223,99,226
])


(def day6-input-test-1
  ["COM)B"
   "B)C"
   "C)D"
   "D)E"
   "E)F"
   "B)G"
   "G)H"
   "D)I"
   "E)J"
   "J)K"
   "K)L"])

(def day6-input-test-2
  ["COM)B"
   "B)C"
   "C)D"
   "D)E"
   "E)F"
   "B)G"
   "G)H"
   "D)I"
   "E)J"
   "J)K"
   "K)L"
   "K)YOU"
   "I)SAN"])

(def day6-input
  ["HL2)HP5"
   "PD5)SVL"
   "GGS)YTS"
   "59G)C67"
   "V3R)BTY"
   "B9B)4QH"
   "P42)PNP"
   "3MR)YPD"
   "HPL)T7M"
   "9T9)BV2"
   "QTG)LZM"
   "LYL)2K1"
   "ZM2)CCK"
   "983)QLL"
   "WYK)SKS"
   "G2H)5QT"
   "GW2)39Q"
   "K68)HBF"
   "FLF)GYC"
   "Y8H)B69"
   "H61)TSN"
   "J6L)PZH"
   "BD3)WSD"
   "321)HZ7"
   "FC4)X58"
   "7M7)SR7"
   "KXX)S7P"
   "JBT)HFR"
   "DPQ)96V"
   "47S)36P"
   "JQ8)FJL"
   "1PX)J2L"
   "DR8)HJ5"
   "1P6)GS4"
   "LXJ)WDK"
   "8BB)LYL"
   "TPD)9XH"
   "5ZB)TML"
   "ZYQ)DLC"
   "1D2)L82"
   "SZX)TLH"
   "3HG)2Z4"
   "T16)DT9"
   "TDS)8PD"
   "Y9F)VNZ"
   "7NW)LQD"
   "F5W)88Z"
   "YQH)N7L"
   "TRH)V7C"
   "FHB)WDL"
   "DT3)BHQ"
   "4Q8)MXM"
   "J1X)5DP"
   "172)6NG"
   "SR7)FLL"
   "DT9)PZP"
   "M8V)FY6"
   "RXG)Y4B"
   "H5F)9G9"
   "B53)F72"
   "9JQ)2WM"
   "422)BQD"
   "96B)6XD"
   "F89)LCS"
   "F59)9H9"
   "J73)JQ9"
   "PNV)BC3"
   "B84)N4Y"
   "9FC)7L8"
   "2ZK)79Z"
   "7CM)6R3"
   "2LL)N46"
   "DXF)2RH"
   "C6Q)JVW"
   "96V)4FT"
   "G86)3CT"
   "Y4D)33X"
   "MXM)BCN"
   "KRR)S7L"
   "BKX)8FF"
   "RQ7)RLG"
   "6Q5)93D"
   "YCZ)J41"
   "ZSM)QGQ"
   "R72)2QY"
   "848)SZX"
   "93D)Z9Q"
   "X5S)T2M"
   "1H4)6Q5"
   "3F1)M8L"
   "Y4W)FLF"
   "K7D)1DD"
   "CGL)8VF"
   "N46)LCX"
   "WDL)XHZ"
   "YZ4)SC1"
   "6K8)D4Z"
   "Z2B)F5J"
   "SQF)1BV"
   "NK2)BXT"
   "SF7)H5Y"
   "HBF)2GM"
   "7RN)5N6"
   "9XH)YGR"
   "P7L)HWR"
   "CRR)5JF"
   "GGG)L41"
   "3JH)HVX"
   "BCH)LNN"
   "4BG)XGX"
   "JCZ)Q5T"
   "SYH)H2H"
   "CLB)TTD"
   "1QW)MX8"
   "F58)9C6"
   "DZY)B84"
   "D76)LX2"
   "JDC)RKC"
   "55J)B9B"
   "DQY)1RB"
   "41Q)VN9"
   "TQW)CY7"
   "G1F)SYH"
   "9MQ)G1F"
   "H1W)3LQ"
   "Y5C)QBJ"
   "CCK)PYL"
   "C6D)P9Y"
   "837)N5C"
   "ZKQ)SPV"
   "DT9)37T"
   "FSQ)GQG"
   "3GY)6BC"
   "TP6)JWB"
   "X42)9Y6"
   "Z9Q)3G2"
   "FF9)CFX"
   "WJ8)873"
   "TPP)VK6"
   "5ZY)DT3"
   "1LM)63J"
   "21Z)SQF"
   "RQT)VQT"
   "CSP)H3T"
   "3PC)S6B"
   "337)D7H"
   "HZ7)XRK"
   "36P)8VL"
   "R2C)LTF"
   "NXN)5DQ"
   "S24)5PL"
   "X8H)7D5"
   "LW1)GYR"
   "H14)9SQ"
   "P15)76B"
   "31W)NX3"
   "QTH)97M"
   "35S)7XM"
   "DK7)VGQ"
   "VNZ)BGT"
   "T6V)1MB"
   "JK4)H92"
   "BV1)ZPR"
   "3YG)7WM"
   "YNL)JQ8"
   "H92)C6G"
   "5GH)R7B"
   "4SK)X7R"
   "KYK)VTJ"
   "DWX)13Z"
   "2QN)XC3"
   "MPF)CFJ"
   "2DW)G9R"
   "KGF)V15"
   "BQD)W9L"
   "CX6)LRG"
   "TGZ)KC3"
   "1S6)YCZ"
   "S7L)3JH"
   "K83)C6Q"
   "H1X)K9L"
   "ZPV)QYC"
   "Z7D)CMZ"
   "PVY)SNN"
   "RVP)5B4"
   "4SX)5J5"
   "WK5)C4P"
   "XZ3)8XF"
   "T3J)337"
   "X5D)DR8"
   "S6B)9FC"
   "4Z9)1F3"
   "HF3)VCN"
   "C37)2H4"
   "71S)NYD"
   "1MB)7CH"
   "TXD)LYX"
   "SF3)P7L"
   "X4Z)VX8"
   "XRL)RTX"
   "F7V)QMZ"
   "VR9)MHV"
   "F6P)4FB"
   "SC1)N9V"
   "QSH)SG5"
   "GVP)5DL"
   "C4G)T1T"
   "VH1)RH4"
   "MZW)4W5"
   "R7B)MRX"
   "P4F)PY4"
   "7YW)577"
   "Y1Y)NGJ"
   "642)M1Z"
   "BLQ)Y4H"
   "3DT)PRB"
   "DPQ)K8G"
   "JKL)N97"
   "7KZ)ZY4"
   "CX6)8RN"
   "WM5)W5L"
   "QBM)NK2"
   "2Z4)X8H"
   "T1T)5BV"
   "2GM)5WD"
   "LZM)V3R"
   "S61)RN6"
   "744)BD2"
   "VQT)WBH"
   "QVF)YGJ"
   "P4B)XCQ"
   "J41)3FX"
   "NCP)BL5"
   "1NK)VPM"
   "9H9)47S"
   "T92)5X8"
   "W9W)439"
   "ST9)HTJ"
   "SMD)1NK"
   "DGW)2QN"
   "T7N)LDH"
   "Y9B)3HG"
   "BBW)3DP"
   "QQS)1H3"
   "YNM)P4F"
   "J4X)KK5"
   "XHS)FJP"
   "L82)HVP"
   "K9L)2RT"
   "1VG)35S"
   "7GK)RD6"
   "1QV)BL4"
   "5NM)Z91"
   "FK8)DWX"
   "JQ9)7TN"
   "5J5)X7L"
   "5X8)837"
   "1DD)VYM"
   "PKR)1QV"
   "V32)Y5C"
   "QMZ)XSF"
   "CPY)8KQ"
   "Y23)DQH"
   "P9Y)YBY"
   "DQH)QQS"
   "S98)VB3"
   "DGF)MS9"
   "MTR)T5J"
   "PZP)6CK"
   "G6T)3G9"
   "JYX)ZSM"
   "9G9)J2Q"
   "F2F)NF1"
   "8X9)JGY"
   "7N3)91G"
   "CJX)PCK"
   "3BR)FP2"
   "HTQ)JF1"
   "XW6)XXN"
   "Y47)LZQ"
   "ZZZ)Y23"
   "VYM)TXW"
   "9G6)27H"
   "Q5T)YLK"
   "BV2)C1N"
   "GZ4)K83"
   "QXJ)JYX"
   "M8P)YSL"
   "741)VSZ"
   "QXP)BFG"
   "X2H)TJ1"
   "H2H)G1T"
   "L41)NH6"
   "QBJ)8BB"
   "XN5)LTC"
   "26R)W2M"
   "2N6)YJD"
   "3LN)XHS"
   "LRG)V65"
   "4W5)B8Y"
   "HG6)QC9"
   "Z3L)GFD"
   "MLK)Y1Y"
   "13Z)LCC"
   "4QH)G2H"
   "XSF)M8R"
   "VH7)K7D"
   "V8N)YBV"
   "751)7CM"
   "NW1)P5H"
   "7FK)SHV"
   "R6J)V32"
   "TK7)H61"
   "2WM)26R"
   "8RN)5VS"
   "BSF)5ZB"
   "WW5)QXV"
   "N42)2JB"
   "FKG)V4R"
   "Q5T)7GK"
   "XBL)546"
   "1TC)Z4Y"
   "4F9)ZKZ"
   "XRK)J4X"
   "JZK)XN5"
   "W23)GZB"
   "V65)TQW"
   "BF7)DXT"
   "726)3F1"
   "L84)M8P"
   "PDX)9QY"
   "7D4)QSN"
   "439)SCX"
   "WBL)X42"
   "9NG)P36"
   "R51)R17"
   "Z9F)321"
   "SSS)NSV"
   "DQ4)P15"
   "R2D)2J4"
   "LDH)V8N"
   "96V)8J9"
   "J9C)JDC"
   "YBD)RQ3"
   "7FK)RSW"
   "YJD)1X7"
   "LYX)VSR"
   "HWZ)1T5"
   "W32)B53"
   "CT7)FPL"
   "YLH)HS7"
   "C67)2F6"
   "TGL)1D2"
   "XH1)751"
   "B95)TP6"
   "918)97J"
   "LC7)52P"
   "4W5)Y9F"
   "5DP)XRP"
   "JF1)KXX"
   "95P)8NP"
   "SR8)HG8"
   "5ZB)D9Q"
   "NRG)F2Z"
   "FMT)J6S"
   "2RC)DGF"
   "4GZ)BXH"
   "4FB)LWM"
   "2YN)69M"
   "PCK)9VS"
   "9W8)L84"
   "SMH)JHR"
   "B2X)WM5"
   "96B)BL2"
   "F7V)R3L"
   "DR3)JBY"
   "ZF8)WRJ"
   "1RB)KTP"
   "TWN)5QY"
   "N7L)WLK"
   "XM8)NRG"
   "T84)6K8"
   "FY6)XDY"
   "6GC)B9W"
   "HFR)JRG"
   "6BC)SYX"
   "MM1)53N"
   "TDS)KPB"
   "CSD)VJN"
   "XDC)DK7"
   "6GV)KRR"
   "5JW)15B"
   "VTJ)SMD"
   "MMS)4Z9"
   "7WM)L7Q"
   "7XM)3PC"
   "QGQ)2GB"
   "WXR)G6T"
   "MTP)65C"
   "5RP)C4G"
   "PP3)Y2M"
   "9SV)27Y"
   "1ZD)2ZK"
   "D2B)7TC"
   "5BV)9MQ"
   "V15)844"
   "CMZ)1VG"
   "6SH)BKX"
   "C41)366"
   "3V4)DPQ"
   "8BB)MZW"
   "HXL)X88"
   "2YN)JKL"
   "YPD)9PN"
   "SPV)YOU"
   "64L)S24"
   "VGK)ZF1"
   "ZJN)3WG"
   "CSP)T7N"
   "7CH)MWB"
   "3DP)YNN"
   "37T)3V4"
   "Z5N)J1Z"
   "578)FPJ"
   "QYC)YNL"
   "52P)FXJ"
   "844)3GY"
   "PWJ)S98"
   "RB9)M14"
   "T84)F89"
   "C58)TCC"
   "YVT)XW6"
   "P49)STW"
   "ZPR)TPD"
   "Y4W)QFY"
   "BNJ)VGT"
   "3FX)1TC"
   "SP3)FNK"
   "B4Y)PKR"
   "WHY)TDS"
   "VVL)Z5D"
   "C99)GS9"
   "3WQ)MST"
   "33X)7D4"
   "BLD)9JQ"
   "MQN)QRP"
   "M8R)ST9"
   "5DL)1P6"
   "5QY)D2V"
   "X7R)LSR"
   "BXT)MG4"
   "6TQ)LVD"
   "XZQ)2KZ"
   "PKW)V3X"
   "QXP)95S"
   "RRY)WLQ"
   "KK5)VH7"
   "PCG)1RS"
   "SYX)SKW"
   "GS9)ZPV"
   "JBT)62J"
   "N42)PJF"
   "5QT)JYF"
   "QMQ)QRD"
   "W1P)WJY"
   "YD2)51R"
   "XPL)4L3"
   "RXS)ZF8"
   "B8B)3ZN"
   "JGY)R51"
   "9QY)WXJ"
   "SHY)Y9B"
   "HG8)TKR"
   "L7Q)SZG"
   "NYM)9T9"
   "ZY4)6SF"
   "879)41Q"
   "YBY)LBZ"
   "RNN)J9C"
   "LB6)XQG"
   "48W)XPL"
   "W5K)TYT"
   "JHR)94L"
   "2P8)QBM"
   "ZW3)BF7"
   "JBD)1GF"
   "X4Z)9NG"
   "HC7)PD5"
   "7JK)GNY"
   "HQB)31W"
   "M8L)JK4"
   "LBZ)7NP"
   "FC4)932"
   "KGF)WW6"
   "6R3)Z2B"
   "1MM)3F3"
   "WXR)8BP"
   "SM6)JSD"
   "8WV)6C1"
   "Z9M)S99"
   "GZB)MTR"
   "39Q)1VY"
   "VR9)PBP"
   "B6D)MLC"
   "DJW)YTY"
   "X7L)R6J"
   "YNN)G86"
   "ZPV)NYM"
   "8KQ)5N5"
   "K1Y)D8G"
   "SVK)M3R"
   "ZZD)XBJ"
   "5VS)M8V"
   "5N5)JZK"
   "D2V)Y8H"
   "SZG)FMS"
   "CG2)2S3"
   "2H4)9P8"
   "9XY)SMV"
   "1YJ)3DT"
   "932)NDV"
   "3PK)PKW"
   "8PD)T4Z"
   "6C1)578"
   "6NK)2LL"
   "NF1)H4Z"
   "KC3)YQ2"
   "1F3)B9V"
   "XLH)H1W"
   "1RS)YQH"
   "R3L)92R"
   "COM)K9X"
   "TTD)BNJ"
   "4SV)5HZ"
   "549)J2W"
   "S96)4JG"
   "88Z)TK7"
   "7D4)JPH"
   "PYG)F76"
   "TSC)W23"
   "9Y6)48W"
   "S7P)QMQ"
   "15Y)NGF"
   "82B)NL3"
   "2RH)BW5"
   "B8Y)MM1"
   "L6M)JRV"
   "53V)S61"
   "K8G)7YW"
   "N5S)VQ8"
   "W2H)WK7"
   "GS7)3ZK"
   "WSD)965"
   "J2W)7FK"
   "PNS)S6T"
   "XHS)9LF"
   "1Z3)WJ4"
   "7F3)8X9"
   "XRP)1KP"
   "QGR)QNM"
   "5CB)N5N"
   "T21)F2F"
   "FP2)XZ9"
   "7L8)BCH"
   "B9W)KCL"
   "XNB)JFV"
   "H4Z)2V1"
   "C1N)DXF"
   "LTF)918"
   "KMJ)WSW"
   "CYN)Y7D"
   "KG3)XNB"
   "SGC)TXD"
   "KPB)Q9J"
   "Y4B)KNW"
   "MBC)XY7"
   "XY1)717"
   "VTH)RXS"
   "VK6)S96"
   "HP5)T6H"
   "BMC)15Y"
   "X6L)D2B"
   "S99)P6L"
   "642)HWZ"
   "Q9J)V3G"
   "QM5)457"
   "72F)TWN"
   "YTS)K9N"
   "VQ8)QXP"
   "XN5)WMB"
   "Z6B)1Z3"
   "JFV)HZB"
   "NH6)4BY"
   "8VF)RQT"
   "1F3)BP7"
   "1LQ)WF3"
   "RTX)SVK"
   "RV4)572"
   "61D)64D"
   "SQY)XHJ"
   "TFB)KMX"
   "GD7)RRY"
   "TJ1)FC4"
   "J5K)PYG"
   "WLK)DGW"
   "PYL)F58"
   "PY4)X61"
   "K5P)W32"
   "SMD)GSG"
   "SY2)XLH"
   "43L)XX5"
   "4DJ)NVS"
   "GF5)T8J"
   "R6J)T16"
   "6NG)W1R"
   "LCS)6WZ"
   "N9V)JBN"
   "H9N)86Z"
   "NS9)3MR"
   "C2R)DLH"
   "NM3)9H4"
   "YSL)CQC"
   "N8X)GZ4"
   "XVG)Z9F"
   "FPL)LXJ"
   "PZH)ZJN"
   "B9V)W2H"
   "XDC)PNS"
   "873)V82"
   "Q6L)S21"
   "P4R)642"
   "YQ2)QMR"
   "C66)JJ2"
   "CFJ)FFQ"
   "NDV)MDT"
   "D44)5ZY"
   "HND)B8B"
   "P67)PCG"
   "QC9)C58"
   "6Z5)3PK"
   "N5N)CPY"
   "6NG)N8P"
   "6ND)LC7"
   "2RT)GBM"
   "GWW)WJB"
   "5DQ)3WS"
   "5WD)1MM"
   "SQY)1LM"
   "STW)2HX"
   "R2T)1S1"
   "8BP)XMG"
   "VPM)PVP"
   "HWR)858"
   "N8P)ZDH"
   "3G2)4KC"
   "T8J)5HL"
   "VLG)WBL"
   "ZKZ)QM5"
   "4L3)5PN"
   "G9R)WYK"
   "SH1)K2B"
   "JRG)9MN"
   "WBH)B7R"
   "4FT)Y72"
   "LCX)VL5"
   "1H4)1XQ"
   "DLH)848"
   "V3X)7F3"
   "CT7)SF7"
   "M6J)Q7R"
   "5B4)YLH"
   "SNN)XZQ"
   "5GH)DV3"
   "GS7)JCZ"
   "D9Q)CJX"
   "MX8)172"
   "MG4)J73"
   "RQ3)9G6"
   "FJP)SC6"
   "SKW)NDP"
   "1YN)T21"
   "1H3)GW2"
   "YBD)6PV"
   "62J)MLK"
   "BFG)GGS"
   "GYR)P49"
   "6TQ)8Z3"
   "JSD)87S"
   "V82)53V"
   "HTQ)YNM"
   "CJM)FHB"
   "JBD)SQY"
   "XY7)MPF"
   "RN6)Q4W"
   "VN9)2MT"
   "3VM)61H"
   "RN2)QGR"
   "BV6)BLQ"
   "3WG)XPY"
   "B69)TGL"
   "3VT)HG6"
   "BL2)M1P"
   "1BV)2DW"
   "75P)3WQ"
   "9PN)CG2"
   "DGF)J78"
   "LQD)5GH"
   "L2H)96B"
   "XX5)82W"
   "GYC)SGC"
   "LYL)S9Z"
   "HZB)D8R"
   "K9N)6SH"
   "6R3)S3N"
   "NGJ)VB5"
   "QLL)SH1"
   "366)6Z5"
   "QRP)9XY"
   "NL3)21Z"
   "2G7)MJZ"
   "P6L)RN2"
   "DSH)GBJ"
   "VGQ)95P"
   "N97)94D"
   "BD2)X47"
   "T6H)VMN"
   "G1R)B6D"
   "B7R)R72"
   "K9X)1PX"
   "NJ8)2RC"
   "S9Z)ZM2"
   "HW5)FMT"
   "FPJ)M5C"
   "MST)1P9"
   "965)X32"
   "NX3)CGK"
   "717)DR3"
   "1D2)TSC"
   "RXH)H9N"
   "8G2)HPL"
   "D8G)XXT"
   "5QT)4SX"
   "1XS)NHG"
   "M9V)PF2"
   "4FB)744"
   "8NP)5JW"
   "LZM)H14"
   "J9C)484"
   "7TC)Z4B"
   "Z27)1LQ"
   "RKC)GVP"
   "RQC)JBD"
   "LVD)FKJ"
   "GFD)HQB"
   "8J9)1ZD"
   "6SF)SAN"
   "SC8)BK9"
   "VGT)JX7"
   "KCZ)4HT"
   "K9L)MQN"
   "9MY)LZ5"
   "JLP)WVP"
   "HFV)Z7D"
   "J1Z)LB6"
   "X61)T92"
   "WW6)VTH"
   "KNC)NL4"
   "C4P)RSJ"
   "QNM)TFB"
   "N12)983"
   "3JJ)QR5"
   "LZ5)H84"
   "KTP)7JK"
   "3WB)Q6L"
   "MS9)YW7"
   "DXT)Y4W"
   "3G9)LVC"
   "2K1)1YJ"
   "577)ZQT"
   "7NP)Y47"
   "PVP)ZW3"
   "V3H)SSS"
   "ZVM)ZZZ"
   "1GF)ZYQ"
   "HVX)R2D"
   "J9G)4GZ"
   "3FX)CX6"
   "97J)X4Z"
   "WMB)M2K"
   "9LF)B95"
   "QBJ)HF3"
   "JWB)292"
   "QRD)P42"
   "Q4W)RV4"
   "XPY)M5Y"
   "5DQ)JGR"
   "95S)GYM"
   "T7M)SY2"
   "6GV)PNV"
   "P5H)7YV"
   "6XD)FVC"
   "ZRP)LWH"
   "BP7)W78"
   "6G4)741"
   "G2B)HTQ"
   "F2Z)D6P"
   "XZ9)XVG"
   "T7M)1H4"
   "1X7)55J"
   "94D)9ZH"
   "HJ5)R2C"
   "WSW)3VT"
   "2JB)CLB"
   "91G)GGG"
   "S21)NJ8"
   "1Z3)5RP"
   "15B)WXR"
   "Q63)PMD"
   "18C)C2R"
   "97M)DQ4"
   "MRX)Y4D"
   "82W)BBW"
   "QR5)2P8"
   "CYN)H5F"
   "WRJ)PWK"
   "XCQ)3V1"
   "BFM)8LX"
   "69M)HGF"
   "LCC)9W8"
   "15Y)71S"
   "WLF)CRR"
   "MDT)549"
   "918)19W"
   "J9B)82B"
   "H6R)HL2"
   "2S3)1XS"
   "5JW)6ND"
   "WF3)NCP"
   "XGX)5CH"
   "6WZ)SHY"
   "J67)P4B"
   "VSR)J6N"
   "9ZH)35Y"
   "MRJ)DSH"
   "86Z)X2H"
   "TXW)WJ8"
   "3LQ)N8X"
   "9H4)3LN"
   "KK5)4SK"
   "NHG)K4C"
   "GYM)43L"
   "XQ4)KDX"
   "Z5D)DZY"
   "H4Z)JBT"
   "BK9)KYK"
   "1KP)BV6"
   "44Y)T6V"
   "T1T)TRH"
   "95Q)XZ3"
   "47X)375"
   "PRB)MJX"
   "VL5)FR1"
   "CGK)7RN"
   "5CH)9BB"
   "5WN)NVY"
   "SKS)YVT"
   "DV3)SM6"
   "63J)ZRP"
   "7YV)GY7"
   "PKW)T3J"
   "JRV)XQ4"
   "2WM)XWK"
   "F5J)GF5"
   "5PL)5WN"
   "LWH)J67"
   "WJY)N39"
   "N12)3WB"
   "FLL)RB9"
   "T5J)J9B"
   "FFQ)YD2"
   "R2D)SC8"
   "VX8)6G4"
   "VMN)PP3"
   "NDP)V3H"
   "V8N)2XQ"
   "8VL)GD7"
   "1S1)4F9"
   "GHT)8G2"
   "T8V)4T3"
   "DLC)MBC"
   "1RS)VH1"
   "VN9)SMH"
   "N39)RNN"
   "W78)SCY"
   "3F3)Z17"
   "8LX)ZQY"
   "3VT)B11"
   "9BB)SP3"
   "D8R)NW1"
   "NGF)ZL8"
   "NVS)G2B"
   "ZQY)4FY"
   "BHQ)C99"
   "2KZ)HFV"
   "D6P)NM3"
   "YTY)4DJ"
   "6PV)61D"
   "TK7)1WL"
   "1VG)F7V"
   "5JF)75P"
   "XWK)LZZ"
   "V7C)NXN"
   "RSJ)XY1"
   "BCN)K5P"
   "TCC)3YG"
   "V4R)RHF"
   "1P9)NHK"
   "JJ2)CW6"
   "SCY)CYN"
   "Z17)ZZD"
   "VMQ)BV1"
   "XMG)44Y"
   "H84)726"
   "R72)KMJ"
   "546)WK5"
   "T4Z)QSH"
   "CFX)WLF"
   "JGR)TGZ"
   "V7C)HXL"
   "PBP)RB5"
   "KMX)7M7"
   "N65)MMS"
   "XXN)9SV"
   "VLN)422"
   "RTX)8MY"
   "J6S)TTH"
   "YGJ)4SV"
   "FN9)59G"
   "NYD)H5N"
   "YW7)8WV"
   "GNY)7NW"
   "TYT)YZ4"
   "BL2)64L"
   "HS7)P4R"
   "XHJ)XDC"
   "N5C)HND"
   "CW6)W9W"
   "8FF)3YH"
   "HD3)Z6B"
   "2J4)J9G"
   "7G7)KM7"
   "S6T)BLD"
   "WDK)Q63"
   "PJF)PH3"
   "ZGX)DJW"
   "JBY)HCG"
   "SMV)M9V"
   "3ZN)B2X"
   "RB5)VMQ"
   "XXT)YBD"
   "35Y)5BH"
   "ZF1)LW1"
   "LTC)D44"
   "SHV)C41"
   "WJB)HD3"
   "MHV)VLG"
   "W5L)FSQ"
   "FVC)QTH"
   "QR5)FLP"
   "8MY)C6D"
   "KDX)J6L"
   "ZQT)95Q"
   "SH1)WHY"
   "BL4)JLP"
   "VB3)RQ7"
   "GY7)ZGX"
   "RH4)XNZ"
   "D4Z)VFR"
   "ZZZ)PDX"
   "NM3)QTG"
   "6CK)XRL"
   "92R)XH1"
   "LBZ)SR8"
   "JNM)BTB"
   "Q7R)FK8"
   "M2K)JV2"
   "457)1S6"
   "Q6L)B4Y"
   "BC3)2G7"
   "VJN)6NK"
   "B9W)CGL"
   "WVP)BSF"
   "HVP)VGK"
   "9MN)879"
   "YLK)QVF"
   "J2Q)QXJ"
   "MLC)47X"
   "NHK)DQY"
   "9G9)18C"
   "2X2)Z9M"
   "375)QGC"
   "572)F59"
   "YJD)RQC"
   "X47)BFM"
   "M14)N5S"
   "S3N)6GC"
   "4T3)C66"
   "4DJ)X5D"
   "P36)W5K"
   "H5Y)SF3"
   "WXJ)CT7"
   "BL5)ZVM"
   "JTW)6GV"
   "M1Z)17T"
   "27Y)ZKQ"
   "1XQ)TTF"
   "LZQ)DPY"
   "Z9F)J1X"
   "NL4)X6L"
   "LCC)HMV"
   "RD6)KCZ"
   "M1P)H6R"
   "TKR)3VM"
   "1WL)CSD"
   "KCL)RVP"
   "QGC)HC9"
   "V3G)Z5N"
   "NSV)9MY"
   "VFR)G1R"
   "M5Y)VR9"
   "S9Z)KNC"
   "YQH)N12"
   "K4C)T84"
   "4JG)T8V"
   "SG5)JNM"
   "79Z)V5L"
   "PWK)MRJ"
   "95Q)J5K"
   "FXJ)FN9"
   "7D5)KGF"
   "K2B)VD1"
   "QSN)4Q8"
   "PD7)SLF"
   "BKX)WW5"
   "YGR)PWJ"
   "LWM)XBL"
   "XDY)3JJ"
   "9VS)D76"
   "3WS)4BG"
   "B11)F6P"
   "94L)N42"
   "VTH)PD7"
   "9H9)N65"
   "YNL)2N6"
   "JV2)CJM"
   "M5C)7G7"
   "RH4)W1P"
   "64D)BD3"
   "FKJ)R2T"
   "Z91)RXH"
   "FJL)RXG"
   "Y2M)DM4"
   "2F6)L6M"
   "27Q)KG3"
   "9SQ)M6J"
   "KMJ)Z27"
   "3ZK)CDV"
   "3YH)1YN"
   "SCX)NS9"
   "C6G)27Q"
   "GQG)5CB"
   "C6L)5C1"
   "MJZ)NP1"
   "BGT)C37"
   "NXN)P67"
   "ZL8)FKG"
   "36P)JTW"
   "LSR)X5S"
   "2XQ)H1X"
   "X58)L2H"
   "G1T)HC7"
   "FLF)Z3L"
   "XX5)7KZ"
   "SLF)VVL"
   "ZDH)TTQ"
   "WK7)F5W"
   "8XF)K1Y"
   "87S)3BR"
   "W2M)GHT"
   "PF2)2YN"
   "17T)HW5"
   "TSN)2X2"
   "Z4Y)BWY"
   "J2L)PVY"
   "76B)K68"
   "H5F)XM8"
   "TTQ)GWW"
   "61H)MTP"
   "JYF)C6L"
   "MBC)BMC"
   "PMD)TPP"
   "MJX)CSP"
   "LZ5)1QW"
   "XBJ)FF9"
   "T2M)6TQ"
   "53N)GS7"
   "2QY)VLN"
   "RLG)72F"
   "HC9)5NM"
   "YBV)7N3"
   ])

(def day7-input
  [3,8,1001,8,10,8,105,1,0,0,21,30,51,72,81,94,175,256,337,418,99999,3,9,101,5,9,9,4,9,99,3,9,1001,9,3,9,1002,9,2,9,1001,9,2,9,1002,9,5,9,4,9,99,3,9,1002,9,4,9,101,4,9,9,102,5,9,9,101,3,9,9,4,9,99,3,9,1002,9,4,9,4,9,99,3,9,102,3,9,9,1001,9,4,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,99])

(def day7-amp-test-software-1
  [3 15 3 16 1002 16 10 16 1 16 15 15 4 15 99 0 0])
(def day7-amp-test-software-2
  [3 23 3 24 1002 24 10 24 1002 23 -1 23
   101 5 23 23 1 24 23 23 4 23 99 0 0])
(def day7-amp-test-software-3
  [3 31 3 32 1002 32 10 32 1001 31 -2 31 1007 31 0 33
   1002 33 7 33 1 33 31 31 1 32 31 31 4 31 99 0 0 0])
(def day7-amp-test-software-4
  [3 26 1001 26 -4 26 3 27 1002 27 2 27 1 27 26
   27 4 27 1001 28 -1 28 1005 28 6 99 0 0 5])
(def day7-amp-test-software-5
  [3 52 1001 52 -5 52 3 53 1 52 56 54 1007 54 5 55 1005 55 26 1001 54
   -5 54 1105 1 12 1 53 54 53 1008 54 0 55 1001 55 1 55 2 53 55 53 4
   53 1001 56 -1 56 1005 56 6 99 0 0 0 0 10])


(def day8-input
  "222220022222222021202222022220222221022222222120222220222222122222222221122222220222222202222222222220220120222222222212202202222202222222220122222222222222222222222022202222122221222222022222222121222220222222022222222222222222221222222222222222222221221120222222222202212222222212222222220022222222222222022222222211212222122220222222022222222120222220222222222222222220122222221222222212222222222220220020222222222212202222222212222222120022222222222220122222222201202222222221222222022222222200222221222222122222222220222222201222222212222222221222221220222222222202212222222202222222021020222222222222122222212121222222022220222221022222222220222221222222222222222222022222200222222222222222222220222121222222222212222202222222222222202022222222222221122222222001212222022222222222122222222002222222222222122222222220022222221222222212222222222220221121222222222222222212222022222222122121222222222222222222212011202222222221222220022222022220222222222222122222222221122222022222222212222222220221220020222222222212202212222202222222221022222222222221222222212002222222222221222221122222222201222222222222022222222220122222222222222202222222222222222222222222222202212202222102222222202222222222222221022222222102212222022221222220022222022212222222222222222222222222022222020222222212222222220221220120222222222202202222222122222222200021222222222222222222212110212222022221222222222222022202222222222222222222222210222222211222222222222222221221221222222222222222222212222002222222002220222222222222222222222120222222022220222221122022222200222220222222122222222212122222100222222212222222220220220220222222222202202202222022222222120020222222122221022222222021202222222222222222022022122000222221222222022222222220022222112222222202222222220220220021222222222202212202222112222222100121222222222221222222212210202222222221222221122222022122222221222222122222222200122222202222222212222222220221222020222222222222212222222012222222102022222222222222022222212111212222022222222222022122022100222220222222222222222221022222202222222222222222220221220121222222222202222202222212222222011021222222222220222222212021222222112221222222222122122012222222222222222222222201222122201222222222222222220222222222222222222202212222222122222222122220222222222221122222222010222222012220222222122022022121222222222222022222222211022122102222222212222222221221220121222222222212222212222222222222110021222222022221222222222002222222202221222220122122022010222220222222022222222221122122222222222222222222221222221122222222222212202202222012122222012220222222022221222222212000222221022221222221122222122211222221222222222222222201022222112222222212222222222221220020222222220212222122222202022222202020222222222221122222222010212220002220222220222122022122222221222222022222222221122022220222222222222222222220220122222222221222222112222002022222020022222222122221222222222001212220222222222221022222122221222220222222022222222201022122001222222222222222222222222022222222220222212022222222022222102122222222022220022222222212222220102201222221222222222012222222222222122222222221122222221222222212222222220222220020222222221212202222222122122222101122222222122220022222202220212221002221222221022022022101222220202222022222222211022022012222222212222222222221222221222222222202102222222022022222121222222222022221222222202222202221202221222221222222122201222222202222222222222200222022012222222202222222222220221121222222220202002022222102122222121020222222122022222222202212212221212220202221122112022200222220222222022222222222122122000222222222222222220222220220222222221202122202222112022222200122222222222020122222212002202220212222222221222012122011222220222222122222222201022022122222222202222222220221222120222222222222212222222122122222212221222222222120222222202100212222112201222220222002022112222220212222022222222202022122211222222212222222222222221222222222221202002222222102122222210220222222122221122222212210212221112211222222022002222012222222212222222222222211122222212222222212222222221220220020222222220212102002222102122222001020222222022122222222222000212222212202222222222102222102222220202222022222222211122120111222222222222222220221222120222222222202202012222102222222202120222222122222122222212121212220022200222220122122122012222222222222222222222222022100002222222202222222221221222020222222222222102002222112222222120021222222122221122222222121222220002210222220222212222100222222202222022222222201122012001222222212220222221220222021222222222002002112222022222222210121222222222122022222202200002221002212212221022202222100222220202222222222222201022211210222222222222222221211221221222222220222002012222012222222012120222222222022222221212001102220122212212222022122122211222220212222022222222200022010100222222202220222222221220122222222220212202022222102222222101221222222122120222221222012112221002221212220122102022022222220220222222222222221122001111222222212220222222212220122222222220222022222222122022222012221222222222221222222202212022221112212202222222122222021222221211222122222222220022111200222222212222222220220220222222222221102202002222022022222211022222222122121022221202100122221002202222221022212022120222221210222022222222200222120102222222202222222221202222222222222220022202212222202222222022221222220022220022221222110202221102211212120022122222102222220222222122222222222222110011222222212222222222211221020222222220202102002222112122222100122222222022220222222200102002222212200212120122012022000222222212222022222222211022022222222222222222222222221221100222222222212202122222012022222222021222220122020122221200100002222022210202021022202222222222220210222122222222200022002222222222212222222222221220201222222221222222222222002122222110122222220222021222220102002122221112202222021222002022020222222212222222222222201022210111222222202222222222222221122222222222002102012222202222222201020222221122122222222011012222222212221202122122002222111222221220222022222222201022212201222222202222222221202221011222222210202012222222022020222111222222222122220122220002102202222102210202022222212022111222221210222022222222202022010212222222222220222220220221212222222212012202022222221022222022022222221122122222220012010012222002200222121122012122120222220211222022221222200222222210222222212222222220202222111222222210102112022212021122222100122222221122120022220211000012222112211222120022102222001222202202222222222222211022121102222222212222222222212220021222222222102012002202200220222001020222222222120122222101001222222022202212221122222222212222201200222122221222201222000220222222212222222221222221111222222222102202012202112220222122220222220022121122220022221022221102211202021022102022000222210222222222221222210022002211222222212220222220202222001222222200112112102202110020222101220222220122020022221212101122221222211212220222212122102222212212222022220222201222210001202222202222222221211222220222222220112122002202221220222102120222221022222222220021110202221212201202221002022222221222212201222122221222200022110221222222202221222220210221020222222202112202002222001120222201121222222222121122222220122202221002222212121122022022020222200220222022222222220222001110222222212221222222210221102222222211102122222202211220222202222222222222220222221220200202222012200212022102222022112222212220222022220222210122011211202222202220222222200221220222222210102022012222002220222022022222221122022222221011221222220022201222122112022122120222200211222222220222202022000110212222202222222221202222210221222210202202212202201121222221221222220022122222222002120012220202220202122122002022102222200202222022221222212222100120212222202222222201222221222221222200102202122202012021222110022222222222121122222100121222202022210212020002202022101222210221222122221222200122200020222222222220222211210220000222222221212212012202211120212201220222221222021122221000210102211122222222222002212022002222212222222022220220200222020002222222202222222202211220120220222211112012212212211121202221021222221122120022221100200022221112211212121112202122200222202201222122220222211122000211202222222221222212222220110220222210102222112212000121202211121222222122021122221002220211210012221202120112112122112222211212222222220220202122000121212222212221222211210220001222222200022202012222222020202100120022222222222022220221022102202002211202122102022122122222221221222022220221201022002201212222202221222222210221002221222201122012112212220222202112222212220222120122221102220011201112201202122000102222212222211210222222220220212022121201202022222222222202210222111221222221022022222212212120202021222202220122120122220121002000222212202222120121222222211222222221222122222220222022012121212022212220222201220220112221222212012012102202010120212011221212220022120122220121201221002002200222021221222022001222212201222122221201210122112111222122222221222221212221220222222212102212222212201021202101222102221022220022222212211012112012221202220120202022120222200212022022222220222022000112222122212222222222201220200221222222202002212202110022222012220012221122121022221001122221011012201212020012202222100222212202122022222202200220101102202122222221222210220221222222222212102022102212210222202020020222222222220222220112020200011122212212020120002122001222201200222022220211210222222122222022212220222222211221002221222221222022212212102020222000221012221122022122222020220110000002220222121111022122201222221211022022221201222221221201212122212222222212221221200222222210012202122222212120222021222112220122220222222020001102122102211212222021002222010222221200022122220212221221200110202122212220222201201221201221222202002002212122111022222212022202221122020221221121100100022022202212121222112222010222202021022122220220212020112121212222212221222200200220012222222200112012212202121222202220221002220022122121221212100022220202211222022011212222010221221220222122222200211221121001222122212221222201210221120220222200112022022222110221222101222112222122022120222222111002220002202222202000112222112121202120222122222220010020221010202122212222222202201222020221222012012212112102220021212210022112222222220020220212122222102202222202100202112122021120212012222122221220111220122020212122202220222212221221202222222212102112002212122121202010021122222222121220220022012120222122212212202111002122212222212002222022222201111021222100202222222222222212202221120222222110212012022122120122112002220120221122120221220221220222011112202212112122022222002202201210122122221210010221202101222122212221222212202220011222222012202222102022010021122122120102222222120221222122121101001012022202121001122022200111200212122222200222200020122002202222202221222211222220202220202220122202222222112020222211122020220022121222221100010202111122211212012102102222211002210012022122202211210120002221202022202221022210212221021220212010012110112222010122012120021111220122022021222010202220222112020212221112112022221211220220122222220221011122111021222222202221222221212221002221202201122202112002120020002012022110022222020222220111010101001122222222100022122022020001210212122022201210200121010011202022202220222222211222120220202220002100102002111222212001122021222122220121220110020001221102221202000212222122002102210202122222211220212021002012222122212220222210210221111220212100122111222012102022222210122200122122120221222010110020111002001222212201012122011201200120122022222201100220010212222122202221022212210220022222212220202110212022022101212101021201120022222021221202212211121012202202220020122222221112221121022122210202210221000002222222212221222221202222112220202222112001012112022122022202122010122122020122220222001111220222122202220111122222010221212211122022212222202221112202202122212221122212222221022201202202202100002122021221002211021000220122220022221221021112011112012222020020022222110121211001222122220202112220221212212222202222222201202222000220212221012100202102111111212120222212220222220121220001011120110212110202101020102022010100212202022122012211211221221122202122202221202210201221000222212010112100222112202021012210220020022122022022221110010211100222011212201112002022022112220212222222100220220010021021202022222220202201202222001222222222002022112022110100012211122000120222220120020010111200020212001222000020222222222220200102222022122200222110110211212022222221202221202220011211222010122001112122022012012010122002021222122020020221211200120212110202211202002222222122210101122022020200001020022211212022202220122222210220120221222120022220002212010122011010220021222022021122222002100202022112112222002100222222010010200122222122001212121210122112202122222221222210200220112102222001002121222122010010000021122021221222120121220211200100012012011212012201202122102102220220122022011201120021011020212222202222201010200222201010212211212012122202112002220010120211020222021120020110201011012202202212122120022022121202201021222022001202120222111022212122222220212111222220011010212022020122102212120100022221220022022022221021121100110021020212001222210210112122210022212100022022100220020121011022222122212221002011211201022122202210011200122212201122122101221020121222121222021222100100100122100212101111112222102111212122122122201200020211021120222222222221012110010201200010202110212120202222021000101202012111122022021222122100122020102002022212220221122122101012210221222222021220101022200010222022202222122022000200210122222012220220222012220100022002102201021122220122220111011001012212101212012100112222202102210020222022002210211010101112012022202221221001121201211102222122020120212202220022002021012211220122222221021000120202200022100212001022222122111211201210122122021200220010010212022222212222220021100211020200202221112021102002211001111022211002021122221222121211211011212012122212200210212022112210210112222022212201120220120210002222222222212010002202200010222021102220222202110112022021212220222222022121221122210020212102012202120101002122011022212001122222211222000101012000112222222222021222202210110122212212022221002112122201202202010110120022022122122211120220211022121202220210222122110100200000222122122221012222001102022220222220201021000222100002222111101102012122121012212222111002022022220022221120011021002112011222211222202122202222201201022222202222211001222022201220202221211210101211121210212122110020212012222102120211200012221022222020110202221101120212111222111100002222202111200112022022000221020022021201022021212221221212122202200210222100101112022012001020002200021210020122222220200222101100202212221222021110202122100221201110122022200201221012111222120122202221110010222212000122222021212210122102212221102102002102121022122020010211000200021002021202212100002122021001200211022122000220120112102201111120202222210012210220220002212110121100012112012221002210012020122022122020002120011020020022010222101200202120210122211211222222220202120122212101021220202220012020222221012012202110011102202102202101110022122121220100202110111101201210201000112000211022120012211122021011110111021021012010022012110111020100121202010020110222010021121200021201221112111122220020111")


(def day9-input
  [1102 34463338 34463338 63 1007 63 34463338 63 1005 63 53 1101 0 3 1000 109 988 209 12 9 1000 209
  6 209 3 203 0 1008 1000 1 63 1005 63 65 1008 1000 2 63 1005 63 904 1008 1000 0 63 1005 63 58 4 25
  104 0 99 4 0 104 0 99 4 17 104 0 99 0 0 1102 1 39 1013 1102 1 21 1018 1101 0 336 1027 1102 1 38
  1012 1101 534 0 1025 1101 539 0 1024 1101 0 380 1023 1102 1 23 1014 1102 29 1 1000 1102 24 1 1019
  1102 1 28 1011 1101 339 0 1026 1101 31 0 1005 1102 36 1 1017 1102 26 1 1007 1102 1 407 1028 1101
  387 0 1022 1101 0 30 1001 1101 34 0 1010 1102 1 32 1006 1101 0 1 1021 1102 27 1 1008 1102 22 1
  1004 1102 1 20 1015 1101 0 37 1016 1101 0 0 1020 1102 1 398 1029 1101 25 0 1009 1101 0 35 1003
  1101 33 0 1002 109 27 1206 -6 197 1001 64 1 64 1105 1 199 4 187 1002 64 2 64 109 -22 2107 26 3 63
  1005 63 217 4 205 1105 1 221 1001 64 1 64 1002 64 2 64 109 17 21107 40 39 -8 1005 1014 241 1001 64
  1 64 1105 1 243 4 227 1002 64 2 64 109 -8 1206 6 261 4 249 1001 64 1 64 1106 0 261 1002 64 2 64
  109 -7 2108 24 0 63 1005 63 281 1001 64 1 64 1105 1 283 4 267 1002 64 2 64 109 11 21102 41 1 -3
  1008 1015 42 63 1005 63 303 1105 1 309 4 289 1001 64 1 64 1002 64 2 64 109 1 1205 2 327 4 315 1001
  64 1 64 1105 1 327 1002 64 2 64 109 10 2106 0 -2 1106 0 345 4 333 1001 64 1 64 1002 64 2 64 109
  -15 21102 42 1 3 1008 1017 42 63 1005 63 367 4 351 1105 1 371 1001 64 1 64 1002 64 2 64 109 -1
  2105 1 10 1001 64 1 64 1105 1 389 4 377 1002 64 2 64 109 24 2106 0 -9 4 395 1001 64 1 64 1105 1
  407 1002 64 2 64 109 -30 1208 -2 32 63 1005 63 427 1001 64 1 64 1106 0 429 4 413 1002 64 2 64 109
  2 1201 0 0 63 1008 63 27 63 1005 63 449 1106 0 455 4 435 1001 64 1 64 1002 64 2 64 109 5 21107 43
  44 0 1005 1014 473 4 461 1106 0 477 1001 64 1 64 1002 64 2 64 109 -16 1202 3 1 63 1008 63 33 63
  1005 63 501 1001 64 1 64 1106 0 503 4 483 1002 64 2 64 109 10 1207 -4 21 63 1005 63 523 1001 64 1
  64 1106 0 525 4 509 1002 64 2 64 109 11 2105 1 5 4 531 1106 0 543 1001 64 1 64 1002 64 2 64 109 -8
  21101 44 0 5 1008 1016 47 63 1005 63 563 1106 0 569 4 549 1001 64 1 64 1002 64 2 64 109 -13 2102 1
  8 63 1008 63 34 63 1005 63 593 1001 64 1 64 1105 1 595 4 575 1002 64 2 64 109 8 1208 -1 31 63 1005
  63 617 4 601 1001 64 1 64 1106 0 617 1002 64 2 64 109 -8 2108 33 4 63 1005 63 635 4 623 1105 1 639
  1001 64 1 64 1002 64 2 64 109 10 1202 -1 1 63 1008 63 26 63 1005 63 665 4 645 1001 64 1 64 1105 1
  665 1002 64 2 64 109 -9 2107 30 1 63 1005 63 685 1001 64 1 64 1105 1 687 4 671 1002 64 2 64 109 25
  1205 -4 703 1001 64 1 64 1105 1 705 4 693 1002 64 2 64 109 -19 2101 0 -5 63 1008 63 26 63 1005 63
  725 1105 1 731 4 711 1001 64 1 64 1002 64 2 64 109 6 1207 -2 26 63 1005 63 749 4 737 1105 1 753
  1001 64 1 64 1002 64 2 64 109 -10 21108 45 46 9 1005 1010 769 1105 1 775 4 759 1001 64 1 64 1002
  64 2 64 109 -10 1201 10 0 63 1008 63 30 63 1005 63 801 4 781 1001 64 1 64 1106 0 801 1002 64 2 64
  109 21 21108 46 46 3 1005 1015 819 4 807 1106 0 823 1001 64 1 64 1002 64 2 64 109 -4 2102 1 -3 63
  1008 63 31 63 1005 63 849 4 829 1001 64 1 64 1106 0 849 1002 64 2 64 109 -5 2101 0 1 63 1008 63 22
  63 1005 63 875 4 855 1001 64 1 64 1105 1 875 1002 64 2 64 109 17 21101 47 0 -3 1008 1017 47 63
  1005 63 897 4 881 1105 1 901 1001 64 1 64 4 64 99 21101 0 27 1 21102 1 915 0 1105 1 922 21201 1
  38480 1 204 1 99 109 3 1207 -2 3 63 1005 63 964 21201 -2 -1 1 21101 0 942 0 1106 0 922 21202 1 1
  -1 21201 -2 -3 1 21101 957 0 0 1105 1 922 22201 1 -1 -2 1106 0 968 22101 0 -2 -2 109 -3 2105 1 0
  ])
