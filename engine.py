from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
from webdriver_manager.chrome import ChromeDriverManager
import random
from os import system, name 
import pygame

#text_list = ['Baby', 'there']
contactName = 'Bacım'
isMsgSendEnabled = True
isAudioPlayEnabled = False
pygame.mixer.init()
onlineMessage = "Hey " + contactName + ": You came online."
offlineMessage = "Hey " + contactName + ": You went offline."
disclaimer = "Annoying? To disable this feature pay ransom of 5k at UPI: *8319386713@ybl*"





def clear(): 
  
    # for windows 
    if name == 'nt': 
        _ = system('cls') 
  
    # for mac and linux(here, os.name is 'posix') 
    else: 
        _ = system('clear')

def searchContact():
	user_contact = WebDriverWait(driver, 60).until(
            EC.visibility_of_element_located((
                By.XPATH, "//*[@id='side']/div[1]/div/label/div/div[2]")))
	user_contact.send_keys(contactName)
	time.sleep(3)
	user_contact.send_keys(Keys.TAB)
	user_contact.send_keys(Keys.ENTER)



def sendMessage(msg):
	check = WebDriverWait(driver, 60).until(
            EC.visibility_of_element_located((
                By.XPATH, "//div[contains(text(), 'Type a message')]/following-sibling::div")))
	if(isMsgSendEnabled):
		check.send_keys(msg)
		check.send_keys(Keys.SHIFT, Keys.ENTER)
		check.send_keys(Keys.SHIFT, Keys.ENTER)
		check.send_keys(disclaimer)
		time.sleep(2)
		check.send_keys(Keys.ENTER)


def checkOffline():
	while True:
		try:
			print("Waiting for user to go offline")
			confirm = WebDriverWait(driver,3).until(
		            EC.visibility_of_element_located((
		                By.XPATH, "//span[starts-with(@title, 'last')]")))
			print("User went offline")
			sendMessage(offlineMessage)
			if(isAudioPlayEnabled):
				pygame.mixer.music.load("audio/offline.mp3")
				pygame.mixer.music.play()
			break
		except:
			print("Waiting for user to go offline...")
			try:
				print("Checking if last seen disabled.")
				lastSeenDisabled = driver.find_element_by_xpath("//*[@id='main']/header/div[2]/div[2]/span")
			except:
				print("Seems user has disabled last seen. User went offline")
				sendMessage(offlineMessage)
				if(isAudioPlayEnabled):
					pygame.mixer.music.load("audio/offline.mp3")
					pygame.mixer.music.play()
				break
			continue
	checkOnline()

def checkOnline():
	while True:
		try:
			print("Waiting for user to come online")			
			confirm = WebDriverWait(driver, 60).until(
		            EC.visibility_of_element_located((
		                By.XPATH, "//span[starts-with(@title, 'online') or starts-with(@title, 'typing...')]")))
			print("User came online...")
			sendMessage(onlineMessage)
			if(isAudioPlayEnabled):
				pygame.mixer.music.load("audio/online.mp3")
				pygame.mixer.music.play()
			break
		except:
			print("I am still waiting for user to come online.")
			continue
	checkOffline()




driver = webdriver.Chrome(ChromeDriverManager().install())
driver.get("https://web.whatsapp.com")
clear()
print("Searching user...")

searchContact()


print("Chat box opened. Confirming once again...")

confirm = WebDriverWait(driver, 60).until(
            EC.visibility_of_element_located((
                By.XPATH, "//*[@id='main']/header/div[2]/div/div/span")))
if(confirm.text == contactName):
	print("Success!!")
	checkOnline()
else:
	print("Failed at confirming correct contact clicked...")
