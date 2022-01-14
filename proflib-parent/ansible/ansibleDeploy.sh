#ansible-playbook -i env/local/00_inventory.yml pingplaybook.yml
#ansible-playbook -i env/local/00_inventory.yml copyfile.yml
#ansible-playbook -i env/local/00_inventoryDocker.yml  --step createUserPlaybook.yml
#ansible-playbook -i env/local/00_inventoryDocker.yml --step installk8playbook.yml
ansible-playbook -i env/master/00_inventoryDocker.yml --step confMasterK8playbook.yml