;;; Sierra Script 1.0 - (do not remove this comment)
(script# 455)
(include game.sh)
(use Main)
(use Face)
(use Intrface)
(use Game)
(use System)
(use Invent) ;ADD
(use User)

(public
	rm455 0
	
)
(synonyms
	(babe entertainer)
)


(instance rm455 of Room
	(properties
		picture 455
	)
	
	(method (init)
		(super init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
		(self setRegions: FACE setLocales: GIRL setScript: RoomScript)
		(NotifyScript 71 1 104 72)
		(NotifyScript 71 2 133 60)
		(NotifyScript 71 3 104 69)
		(NotifyScript 71 4 135 55)
		(NotifyScript 71 5 133 82)
		(NotifyScript 71 6 138 91)
		;(DrawRect 0 320 21 190)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				;(HandsOff)
				(PersonSays 455 73)
				(theGame changeScore: 100)
				(= currentStatus egoAUTO)
				(++ state)
				(AnimateFace 2 99)
				(= cycles 33)
			)
			(2
				;(HandsOff)
				(EgoSays 455 74)
				(PersonSays 455 75)
				(AnimateFace 2 55)
				(= cycles 33)
			)
			(3
												
				(curRoom newRoom: 450)
			)
		)
	)
	
	(method (handleEvent event &tmp index [str 200])
		(if (event claimed?)
			(return)
		)
		(cond 
			(
				(or
					(Said '(go<out),date/babe')
					(Said 'get/anyword/building')
					(Said 'ask/anyword/date')
					(Said 'ask,get/date')
					(Said 'make/enjoy,date')
					(Said 'let<make/enjoy,date')
					(Said 'anyword/make/enjoy')
					(Said 'go/area,penthouse,backdrop,comedian,club,date,exit,bar,show,dance')
				)
				(cond 
					((not (InRoom iDivorceDecree))
						(EgoSays 455 0)
						(PersonSays 455 1)
						(AnimateFace 4)
					)
					((not larryBuffed)
						(EgoSays 455 2)
						(PersonSays 455 3)
						(AnimateFace 4)
					)
					((not fNotShower)
						(EgoSays 455 4)
						(PersonSays 455 5)
						(AnimateFace 4)
					)
					((not fNotUseSoap)
						(EgoSays 455 4)
						(PersonSays 455 6)
						(AnimateFace 4)
					)
					((not fNotUseDeodorant)
						(EgoSays 455 4)
						(PersonSays 455 7)
						(AnimateFace 4)
					)
					((not (InRoom iOrchids))
						(EgoSays 455 8)
						(PersonSays 455 9)
					)
					(else
						(self changeState: 1)
					)
				)
			)
			((or (Said '/marriage') (Said 'marry/'))
				(Print 455 10)
			)
			((Said 'bang')
				(PersonSays 455 11)
				(AnimateFace 4 4)
			)
			((Said '/neck')
				(Print 455 12)
			)
			((Said '/penthouse,area')
				(PersonSays 455 13)
			)
			((Said 'caress')
				(PersonSays 455 14)
				(AnimateFace 2 5)
			)
			((Said 'embrace')
				(PersonSays 455 15)
				(AnimateFace 2 5)
			)
			((Said '/music,play')
				(EgoSays 455 16)
				(PersonSays 455 17)
				(AnimateFace 2 5)
			)
			((Said 'crap')
				(PersonSays 455 18)
				(AnimateFace 4 4)
			)
			((Said 'address')
				(switch (Random 1 3)
					(1 (PersonSays 455 19))
					(2 (PersonSays 455 20))
					(else  (PersonSays 455 21))
				)
			)
			((Said 'show/ball')
				(EgoSays 455 22)
				(PersonSays 455 23)
				(AnimateFace 2 20)
			)
			((Said 'make/joke')
				(Print 455 24)
				(Print 455 25 #at -1 144)
				(PersonSays 455 26)
				(AnimateFace 7 22)
			)
			((Said 'enjoy/ya,babe')
				(EgoSays 455 27)
				(PersonSays 455 28)
				(AnimateFace 5 44)
			)
			((Said '/pass,backstage')
				(EgoSays 455 29)
				(PersonSays 455 30)
			)
			((Said 'get,gamble/music,buy')
				(EgoSays 455 31)
				(PersonSays 455 32)
			)
			(
				(or
					(Said 'cease/look')
					(Said 'look<cease')
					(Said 'look/bar,area')
					(Said 'done,exit,exit,done,exit,go')
					(Said 'exit,nightstand,(nightstand<up),(get<off,up)/barstool')
				)
				(self changeState: 2)
			)
			((Said 'look>')
				(cond 
					((Said '/calf')
						(PersonSays 455 33)
						(AnimateFace 3 22)
					)
					((Said '/bracelet')
						(Print 455 34)
					)
					((Said '/ear')
						(Print 455 35)
					)
					((Said '/boob')
						(Print 455 36)
						(PersonSays 455 37)
						(AnimateFace 6 12)
					)
					((Said '/keyboard')
						(Print 455 38)
					)
					((Said '[/keyboard,babe,babe]')
						(Print 455 39)
						(PersonSays 455 40)
						(AnimateFace 2 33)
					)
				)
			)
			((Said 'give,throw,backdrop,show,backdrop>')
				(= index (inventory saidMe:))
				(event claimed: FALSE)
				(cond 
					((Said '[/noword]')
						(Print 455 41)
					)
					((not index)
						(Print 455 42)
					)
					((not (index ownedBy: ego))
						(DontHave)
					)
					((Btst fNotShower)
						(PersonSays 455 5)
						(AnimateFace 4)
					)
					((Btst fNotUseSoap)
						(PersonSays 455 6)
						(AnimateFace 4)
					)
					((== index (inventory at: iCreditCard))
						(EgoSays 455 43)
						(PersonSays 455 44)
						(AnimateFace 4)
					)
					((== index (inventory at: iGrass))
						(if (== (index view?) 23)
							(EgoSays 455 45)
							(PersonSays 455 46)
						else
							(EgoSays 455 47)
							(PersonSays 455 48)
						)
						(AnimateFace 4)
					)
					((== index (inventory at: iSoap))
						(EgoSays 455 49)
						(PersonSays 455 50)
						(AnimateFace 4)
					)
					((== index (inventory at: iMoney))
						(EgoSays (Format @str 455 51 dollars))
						(PersonSays 455 52)
						(AnimateFace 4)
					)
					((== index (inventory at: iLandDeed))
						(EgoSays 455 53)
						(PersonSays 455 54)
						(AnimateFace 4)
					)
					((== index (inventory at: iTowel))
						(EgoSays 455 55)
						(PersonSays 455 56)
						(AnimateFace 4)
					)
					((== index (inventory at: iSpaKeycard))
						(EgoSays 455 57)
						(PersonSays 455 58)
						(AnimateFace 2)
					)
					((== index (inventory at: iDivorceDecree))
						(EgoSays 455 59)
						(if (not (ego has: iSpaKeycard))
							(Print 455 60)
							(ego get: iSpaKeycard)
							(Bset fFoundGymKeyAccidentally)
							(Print 455 61)
						)
						(theGame changeScore: 100)
						(PutInRoom iDivorceDecree)
						(PersonSays 455 62)
						(AnimateFace 2)
					)
					((== index (inventory at: iOrchids))
						(cond 
							((and (== orchidMinutes 1) (== orchidSeconds 0))
								(EgoSays 455 63)
								(PersonSays 455 64)
								(AnimateFace 4)
							)
							((not (InRoom iDivorceDecree))
								(EgoSays 455 65)
								(PersonSays 455 66)
								(AnimateFace 4)
							)
							((not (== (index view?) 26))
								(EgoSays 455 67)
								(PersonSays 455 68)
								(AnimateFace 4)
							)
							(else
								(EgoSays 455 69)
								(PersonSays 455 70)
								(theGame changeScore: 100)
								(PutInRoom iOrchids)
								(AnimateFace 2)
							)
						)
					)
					(else
						(EgoSays (Format @str 455 71 (index name?)))
						(PersonSays 455 72)
					)
				)
				(event claimed: TRUE)
			)
			((== (event type?) evMOUSEBUTTON)
				(if (& (event modifiers?) emRIGHT_BUTTON) ;is right_click
					(switch theCursor ;current cursor
						(itemIcon
							(theGame setCursor: 991 (HaveMouse)) ;switch to exit intead of walk
							(event claimed: 1)
						)
						(991 ;if exit
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
						)
						(999 ;just incase they have the walk cursor
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(993 ;just incase they have the arrow cursor
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(996
							(theGame setCursor: 994 (HaveMouse))
							(event claimed: 1)
						)
						(998
							(theGame setCursor: 995 (HaveMouse))
							(event claimed: 1)
						)
						(995
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(994
							(if
								(or 
									(== itemIcon 900)
									(== itemIcon 994)
									(== itemIcon 666)
									(== itemIcon 992)
									(== itemIcon 993)
								)
								(theGame setCursor: 991 (HaveMouse))
							else
								(theGame setCursor: itemIcon (HaveMouse))
								(= theCursor itemIcon)
							)
							(event claimed: 1)
						)
					)                                                                  
				else ;left clicks
					(if 
						(or
							(ClickedInRect 90 112 65 115 event) ;eye1
							(ClickedInRect 125 139 55 60 event) ;eye2
						)
						(if (== theCursor 998) ;clicked look on eyes			
							(PersonSays 455 33)
							(AnimateFace 3 22)
							(event claimed: TRUE)
						)
					)			
					(if (ClickedInRect 47 91 86 106 event) ;ear1
						(if (== theCursor 998) ;clicked look on ear			
							(Print 455 35)
							(event claimed: TRUE)
						)
					)	
					(if (ClickedInRect 128 172 141 157 event) ;bracelet
						(if (== theCursor 998) ;clicked look on bracelet			
							(Print 455 34)
							(event claimed: TRUE)
						)
					)					
					(if (ClickedInRect 120 148 88 93 event) ;lips
						(switch theCursor					
							(998 ;Look
								(Print 70 9)
								(event claimed: TRUE)
							)
						)
					)
					(if
						(or
							(ClickedInRect 28 156 21 45 event) ;hair top
							(ClickedInRect 1 23 21 134 event) ;hair left side
						)
						(switch theCursor					
							(998 ;Look
								(Print 70 5)
								(event claimed: TRUE)
							)
						)
					)
					(if
						(and
							(ClickedInRect 1 194 21 189 event) ;head
							(== (event claimed?) 0) ;ignore if already clicked on eyes, hair, etc...
						)
						(switch theCursor
							(996 ;TALK
								(if (!= DivorceOK 2) 
								(switch (Random 1 14)
									(1
										(Print 455 10)
									)
									(2
										(PersonSays 455 11)
										(AnimateFace 4 4)
									)
									(3
										(Print 455 12)
									)
									(4
										(PersonSays 455 13)
									)
									(5
										(PersonSays 455 14)
										(AnimateFace 2 5)
									)
									(6
										(PersonSays 455 15)
										(AnimateFace 2 5)
									)
									(7
										(EgoSays 455 16)
										(PersonSays 455 17)
										(AnimateFace 2 5)
									)
									(8
										(PersonSays 455 18)
										(AnimateFace 4 4)
									)
									(9
										(switch (Random 1 3)
											(1 (PersonSays 455 19))
											(2 (PersonSays 455 20))
											(else  (PersonSays 455 21))
										)
									)
									(10
										(EgoSays 455 22)
										(PersonSays 455 23)
										(AnimateFace 2 20)
									)
									(11
										(Print 455 24)
										(Print 455 25 #at -1 144)
										(PersonSays 455 26)
										(AnimateFace 7 22)
									)
									(12
										(EgoSays 455 27)
										(PersonSays 455 28)
										(AnimateFace 5 44)
									)
									(13
										(EgoSays 455 29)
										(PersonSays 455 30)
									)
									(14
										(EgoSays 455 31)
										(PersonSays 455 32)
									)
								)
								(event claimed: TRUE)
								)
								(if (== DivorceOK 2)
									(cond 
										((not (InRoom iDivorceDecree))
											(EgoSays 455 0)
											(PersonSays 455 1)
											(AnimateFace 4)
										)
										((not larryBuffed)
											(EgoSays 455 2)
											(PersonSays 455 3)
											(AnimateFace 4)
										)

										((Btst fNotShower)
											(EgoSays 455 4)
											(PersonSays 455 5)
											(AnimateFace 4)
										)
										( (Btst fNotUseSoap)
											(EgoSays 455 4)
											(PersonSays 455 6)
											(AnimateFace 4)
										)
										( (Btst fNotUseDeodorant)

                                             (EgoSays 455 4)
                                             (PersonSays 455 7)
                                             (AnimateFace 4)
                                        )
										((not (InRoom iOrchids))
											(EgoSays 455 8)
											(PersonSays 455 9)
										)
										
									(else
											(self changeState: 1)								
									)

								 )
								)
										
													
	)							
							(994 ;Zipped head
	
								(event claimed: TRUE)
							)						
	
							(998 ;look head
	
								(event claimed: TRUE)
							)
							(995 ;take/hand

								(event claimed: TRUE)
							)
							(21 ;knife
								(AnimateFace 4)
								(event claimed: TRUE)
							)
							(2 ;knife_normal
								(AnimateFace 4)
								(event claimed: TRUE)
							)
							(1 ;iCreditCard
								(cond
									((Btst fNotShower)
										(PersonSays 455 5)
										(AnimateFace 4)
									)
									((Btst fNotUseSoap)
										(PersonSays 455 6)
										(AnimateFace 4)
									)
									(else
										(EgoSays 455 43)
										(PersonSays 455 44)
										(AnimateFace 4)
									)
								)
								(event claimed: TRUE)
							)
							(4 ;iGrass
								(cond
									((Btst fNotShower)
										(PersonSays 455 5)
										(AnimateFace 4)
									)
									((Btst fNotUseSoap)
										(PersonSays 455 6)
										(AnimateFace 4)
									)
									(else										
;;;										(if (== (index view?) 23)
										(if (== ((Inventory at: theCursor) view?) 23)
											(EgoSays 455 45)
											(PersonSays 455 46)
										else
											(EgoSays 455 47)
											(PersonSays 455 48)
										)
										(AnimateFace 4)
									)
								)
								(event claimed: TRUE)
							)
							(5 ; iSoap
								(cond
									((Btst fNotShower)
										(PersonSays 455 5)
										(AnimateFace 4)
									)
									((Btst fNotUseSoap)
										(PersonSays 455 6)
										(AnimateFace 4)
									)
									(else
										(EgoSays 455 49)
										(PersonSays 455 50)
										(AnimateFace 4)
									)
								)
								(event claimed: TRUE)
							)
							(24  ;iMoney
								(cond
									((Btst fNotShower)
										(PersonSays 455 5)
										(AnimateFace 4)
									)
									((Btst fNotUseSoap)
										(PersonSays 455 6)
										(AnimateFace 4)
									)
									(else
										(EgoSays (Format @str 455 51 dollars))
										(PersonSays 455 52)
										(AnimateFace 4)
									)
								)
								(event claimed: TRUE)
							)
							(7 ;iLandDeed
								(cond
									((Btst fNotShower)
										(PersonSays 455 5)
										(AnimateFace 4)
									)
									((Btst fNotUseSoap)
										(PersonSays 455 6)
										(AnimateFace 4)
									)
									(else
										(EgoSays 455 53)
										(PersonSays 455 54)
										(AnimateFace 4)
									)
								)
								(event claimed: TRUE)
							)
							(8 ;iTowel
								(cond
									((Btst fNotShower)
										(PersonSays 455 5)
										(AnimateFace 4)
									)
									((Btst fNotUseSoap)
										(PersonSays 455 6)
										(AnimateFace 4)
									)
									(else
										(EgoSays 455 55)
										(PersonSays 455 56)
										(AnimateFace 4)
									)
								)
								(event claimed: TRUE)
							)
							(9 ;iSpaKeycard
								(cond
									((Btst fNotShower)
										(PersonSays 455 5)
										(AnimateFace 4)
									)
									((Btst fNotUseSoap)
										(PersonSays 455 6)
										(AnimateFace 4)
									)
									(else
										(EgoSays 455 57)
										(PersonSays 455 58)
										(AnimateFace 2)
									)
								)
								(event claimed: TRUE)
							)
							(11 ; orchids
								
								(EgoSays 455 67)
								(PersonSays 455 68)
								(AnimateFace 4)
								
							
							)
							(26 ; flowers
								(cond
									((Btst fNotShower)
										(PersonSays 455 5)
										(AnimateFace 4)
									)
									((Btst fNotUseSoap)
										(PersonSays 455 6)
										(AnimateFace 4)
									)
									(else				
										;(cond  ;can't use COND with AND
										(if
											(and
												(== orchidMinutes 1)
												(== orchidSeconds 0)
											) 
											(EgoSays 455 63)
											(PersonSays 455 64)
											(AnimateFace 4)
											(Format ((Inventory at: iOrchids) name?) {Orquideas}) ;SPANISH
;;;											(Format ((Inventory at: iOrchids) name?) {Orquids}) ;ENGLISH
											(PutInRoom iOrchids) ;remove flowers from larry inv.
											(= itemIcon 900) ;clear menu inv item pic
											(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look
										else
											(cond
												((== DivorceOK 0)
													(EgoSays 455 65)
													(PersonSays 455 66)
													(AnimateFace 4)
												)
;;;												((!= ((inventory indexOf: iOrchids) view?) 26) ;flowers are not lai
												((!= ((Inventory at: iOrchids) view?) 26)
													(EgoSays 455 67)
													(PersonSays 455 68)
													(AnimateFace 4)
													(Format ((Inventory at: iOrchids) name?) {Orquideas}) ;SPANISH
		;;;											(Format ((Inventory at: iOrchids) name?) {Orquids}) ;ENGLISH
													(PutInRoom iOrchids) ;remove flowers from larry inv.
													(= itemIcon 900) ;clear menu inv item pic
													(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look
												)
												;((if (== DivorceOK 1)
												(DivorceOK ;simplified check for "DivorceOK is not 0"
													(EgoSays 455 69)
													(PersonSays 455 70)
													(theGame changeScore: 100)
													(Format ((Inventory at: iOrchids) name?) {Orquideas}) ;SPANISH
;;;													(Format ((Inventory at: iOrchids) name?) {Orquids}) ;ENGLISH
													(PutInRoom iOrchids) ;remove flowers from larry inv.
													(= itemIcon 900) ;clear menu inv item pic
													(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look
													(AnimateFace 2) ;2
													(= DivorceOK 2)
													(cond 
										((not (InRoom iDivorceDecree))
											(EgoSays 455 0)
											(PersonSays 455 1)
											(AnimateFace 4)
										)
										((not larryBuffed)
											(EgoSays 455 2)
											(PersonSays 455 3)
											(AnimateFace 4)
										)

										( (Btst fNotShower)
											(EgoSays 455 4)
											(PersonSays 455 5)
											(AnimateFace 4)
										)
										((Btst fNotUseSoap)
											(EgoSays 455 4)
											(PersonSays 455 6)
											(AnimateFace 4)
										)
										((Btst fNotUseDeodorant)

                                             (EgoSays 455 4)
                                             (PersonSays 455 7)
                                             (AnimateFace 4)
                                        )
										((not (InRoom iOrchids))
											(EgoSays 455 8)
											(PersonSays 455 9)
										)
										
									(else
											(self changeState: 1)								
									)

								 )
								
												)
												  
												  )		
										)
									)
								)
								(event claimed: TRUE)
							)
							(991 ;exit

								
									(theGame setCursor: 998 (HaveMouse))
																(self changeState: 2)
								(event claimed: TRUE)
							)
							(10 ;iDivorceDecree
								(cond
									((Btst fNotShower)
										(PersonSays 455 5)
										(AnimateFace 4)
									)
									((Btst fNotUseSoap)
										(PersonSays 455 6)
										(AnimateFace 4)
									)
									(else
										(EgoSays 455 59)
										(if (not (ego has: iSpaKeycard))
											(Print 455 60)
											(ego get: iSpaKeycard)
											(Bset fFoundGymKeyAccidentally)
											(Print 455 61)
										)
										(theGame changeScore: 100)
										(PutInRoom iDivorceDecree)
										(= itemIcon 900) ;clear menu inv item pic
										(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look
										(= DivorceOK 1)
										(PersonSays 455 62)
										(AnimateFace 2)
									)
								)
								(event claimed: TRUE)
							)
							(else 
								(if (< theCursor 34) ;response for giving other inv items
;;;;;;									(EgoSays (Format @str 455 71 (index name?)))
	
									(EgoSays (Format @str 455 71 ((inventory at: theCursor) name?))) ;DONT FIXED
									(PersonSays 455 72)
									(event claimed: TRUE)
								else
									(event claimed: FALSE)
								)	
							)
						)
					)
					(if 
						(and
							(ClickedInRect 0 320 21 190 event) ;anywhere else 
							(== (event claimed?) 0) ;ignore if already clicked on head, eyes, hair, etc...
						)
						(switch theCursor
							(991 ;exit
								
								(self changeState: 2)
								(theGame setCursor: 998 (HaveMouse))
								(= theCursor 998)
								(HandsOff)
								(event claimed: TRUE)
							)
							(else
								(theGame setCursor: 991 (HaveMouse)) ;switch to exit cursor if clicked outside of head
								(event claimed: TRUE)
							)
						)
					)
				)
			)
		)
	)
)