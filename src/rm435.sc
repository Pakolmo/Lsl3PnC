;;; Sierra Script 1.0 - (do not remove this comment)
(script# 435)
(include game.sh)
(use Main)
(use Face)
(use Intrface)
(use Game)
(use System)
(use User)

(public
	rm435 0
)
(synonyms
	(babe cheri)
)

(local
	local0
	talkedToCherri
)
(instance rm435 of Room
	(properties
		picture 435
	)
	
	(method (init)
		(super init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
		(self setRegions: FACE setLocales: GIRL setScript: RoomScript)
		(NotifyScript 71 1 149 45)
		(NotifyScript 71 2 180 45)
		(NotifyScript 71 3 149 42)
		(NotifyScript 71 4 178 42)
		(NotifyScript 71 5 163 61)
		(NotifyScript 71 6 164 78)
;;;		(DrawRect 120 135 46 60 1)
;;;(DrawRect 198 208 51 65 2)
;;;(DrawRect 88 135 166 189 3)
;;;(DrawRect 163 233 154 189 4)
;;;(DrawRect 0 320 21 190 5)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if local0
					(if (!= showroomState SRknowsAboutDeed)
						(= showroomState SRknowsAboutDeed)
						(= lawyerState LSfree)
						(= newspaperState NSshowroom)
						(theGame changeScore: 25)
					)
				else
					(EgoSays 435 30)
					(PersonSays 435 31)
				)
				(AnimateFace 2 55)
				(= cycles 33)
			)
			(2 (curRoom newRoom: 420))
		)
	)
	
	(method (handleEvent event &tmp index)
		(if (event claimed?)
			(return)
		)
		(cond 
			((Said 'bang')
				(Print 435 0)
				(PersonSays 435 1)
				(AnimateFace 4 4)
			)
			(
				(or
					(Said '/land,deed,dowry')
					(Said '//land,deed,dowry')
				)
				(HandsOff)
				(EgoSays 435 2)
				(PersonSays 435 3)
				(= local0 1)
				(AnimateFace 2 33)
				(= state 0)
				(= cycles 22)
			)
			(
				(or
					(Said '(out<go),dance,show,date/')
					(Said 'get/babe/building')
					(Said 'ask/babe/date')
					(Said 'go/area,backdrop,comedian,club,date,exit,bar,show,dance')
				)
				(EgoSays 435 4)
				(PersonSays 435 5)
			)
			((Said 'address/babe,babe')
				(if (not talkedToCherri)
					(= talkedToCherri TRUE)
					(EgoSays 435 6)
					(PersonSays 435 7)
				else
					(EgoSays 435 8)
					(PersonSays 435 9)
				)
				(AnimateFace 2 20)
			)
			((Said 'show/ball')
				(EgoSays 435 10)
				(PersonSays 435 11)
				(AnimateFace 2 20)
			)
			((or (Said 'enjoy/ya') (Said '/enjoy/ya'))
				(EgoSays 435 12)
				(PersonSays 435 13)
				(AnimateFace 5 44)
			)
			(
				(or
					(Said 'cease/look')
					(Said 'look<cease')
					(Said 'look/area')
					(Said 'done,bye,exit,exit,done,exit,go')
				)
				(self changeState: 1)
			)
			((Said '/building,land,bush,bush')
				(PersonSays 435 14)
			)
			((Said '/dance,show')
				(EgoSays 435 15)
				(PersonSays 435 16)
			)
			((Said 'look>')
				(cond 
					((Said '/calf')
						(PersonSays 435 17)
						(AnimateFace 3 22)
					)
					((Said '/ear')
						(Print 435 18)
					)
					((Said '/boob')
						(Print 435 19)
						(PersonSays 435 20)
						(AnimateFace 6 12)
					)
					((Said '[/area,casino,hotel,babe]')
						(Print 435 21)
						(PersonSays 435 22)
						(AnimateFace 2 33)
					)
				)
			)
			((Said 'give>')
				(= index (inventory saidMe:))
				(event claimed: FALSE)
				(cond 
					((Said '[/noword]')
						(Print 435 23)
					)
					((not index)
						(Print 435 24)
					)
					((not (index ownedBy: ego))
						(DontHave)
					)
					((== index (inventory at: iTowel))
						(PersonSays 435 25)
						(PutInRoom iTowel)
						(theGame changeScore: -10)
						(AnimateFace 2)
					)
					((== index (inventory at: iSoap))
						(PersonSays 435 26)
						(PutInRoom iSoap)
						(theGame changeScore: -10)
						(AnimateFace 2)
					)
					((== index (inventory at: iKnife))
						(PersonSays 435 27)
						(PutInRoom iKnife)
						(theGame changeScore: -10)
						(AnimateFace 2)
					)
					((== index (inventory at: iOrchids))
						(PersonSays 435 28)
						(AnimateFace 4)
					)
					(else
						(PersonSays 435 29)
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
							(theGame setCursor: 995 (HaveMouse))
							(event claimed: 1)
						)
						(998
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(995
							(theGame setCursor: 994 (HaveMouse))
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
					(event claimed: TRUE)
					(switch theCursor
						(8 ;toalla
								
									(HandsOff)
									(PersonSays 435 25)
									;(PutInRoom iTowel) ;fix bad ending
									;(theGame changeScore: -10)
									(AnimateFace 2)
									(HandsOn)
						)
						(5 ; soap
							
									(HandsOff)
									(PersonSays 435 26)
									;(PutInRoom iSoap) ; fix bad ending
									;(theGame changeScore: -10)
									(AnimateFace 2)
									(HandsOn)
								)
						(21 ;knife
								
									(HandsOff)
									(PersonSays 435 27)
									(PutInRoom iKnife)
									(theGame changeScore: -10)
									(= itemIcon 900) ;clear menu inv item pic
									(theGame setCursor: 998 (HaveMouse)) ;clear inv cursor, switch to look
									(AnimateFace 2)
									(HandsOn)
								)
						(11 ;orchids
									(HandsOff)
									(PersonSays 435 28)
									(AnimateFace 4)
									(HandsOn)
								)
						(26 ;orchids lei
									(HandsOff)
									(PersonSays 435 28)
									(AnimateFace 4)
									(HandsOn)
								)

						(996 ;talk
							(HandsOff)
							(if (not talkedToCherri)
								(= talkedToCherri TRUE)
								(EgoSays 435 6)
								(PersonSays 435 7)
							else
								(EgoSays 435 8)
								(PersonSays 435 9)
							(EgoSays 435 2)
							(PersonSays 435 3)
							(= local0 1)
							(AnimateFace 2 33)
							(= state 0)
							(= cycles 22)
								
								
								
							)
							(AnimateFace 2 20)
							(HandsOn)
							

						)
						(994 ;zipper
							(HandsOff)
							(if (Random 0 1) ; 50% chance TRUE
								;(Said 'bang')
								(Print 435 0)
								(PersonSays 435 1)
								(AnimateFace 4 4)	
							else
								;(Said 'show/ball') ;show penis
								(EgoSays 435 10)
								(PersonSays 435 11)
								(AnimateFace 2 20)
							)
							(HandsOn)
						)
						(998 ;look
							
							(cond 
;;;								((Said '/calf')
;;;									(PersonSays 435 17)
;;;									(AnimateFace 3 22)
;;;								)


								((ClickedInRect 120 135 46 60 event) ;add ear1 coords.(DrawRect 120 135 46 60 1)
									(HandsOff)
									(Print 435 18)
									(HandsOn)
								)
								((ClickedInRect 198 208 51 65 event) ;add ear2 coords. (DrawRect 198 208 51 65 2)
									(HandsOff)
									(Print 435 18)
									(HandsOn)
								)
								((ClickedInRect 88 135 166 189 event) ;add Breast1 coords.(DrawRect 88 135 166 189 3)
									(HandsOff)
									(Print 435 19)
									(PersonSays 435 20)
									(AnimateFace 6 12)
									(HandsOn)
								)
								((ClickedInRect 163 233 154 189 event) ;add Breast2 coords. (DrawRect 163 233 154 189 4)
									(HandsOff)
									(Print 435 19)
									(PersonSays 435 20)
									(AnimateFace 6 12)
									(HandsOn)
								)
								((ClickedInRect 0 320 22 190 event) ;leave 0-20y open for menu. (DrawRect 0 320 21 190 5)
									(HandsOff)
								    (Print 435 21)
								    (PersonSays 435 22)
								    (AnimateFace 2 33)
								    (HandsOn)
								)
								(else
									
								    (event claimed: FALSE)
								)									
	
							)
						)
						(991 ;exit
							(HandsOff)
							(theGame setCursor: 996 (HaveMouse))
							(self changeState: 1)
						)
						(else
							(event claimed: 0) 	
						)
												;(if (< theCursor 34)


							)	
						)
					)
				)
			)
		)
