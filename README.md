  # course-management-system Tutorial
This will provide a basic guidline to the beginners who are willing to create a stand alone application with java.                
	The class diagram
	
  ![class diagram](https://firebasestorage.googleapis.com/v0/b/firestorecrud-cdd76.appspot.com/o/course-management-system%2FClass%20Diagram.png?alt=media&token=079498be-d228-4a26-b895-afa9bf903eed)

The EER diagram

![eer](https://firebasestorage.googleapis.com/v0/b/firestorecrud-cdd76.appspot.com/o/course-management-system%2FEER.png?alt=media&token=2642d98d-d567-476e-88fe-656b75d9b506)
	
GIT commands

basic commands
basically local git repository contains three parts. those are

1.working directory (the actual files)

2.index (staging are)
	
	git add filename (will add files from working directory to index)
		
3.HEAD (points to the last commit)

	git commit -m "commit message" (add changes from index to HEAD)
	
basics
	
	git clone path/to/repository (to clone a local repository)
	git clone url(to clone a global repository)
	git config --global user.email your email (to configurations)
	git status (to check the state)
	git push
	git pull 
	git branch <branch name> 
	git checkout <branch name>
	git branch -d <branch name>
	git remote -v
	git diff <source branch> <target branch>
	
git advanced

	git cherry-pick commit-hash (make sure that you are in correct branch. then can pick the commit from another branch into it)
	git push -f origin commitid^:branch (to delete last commit message in remote repository)
	git reset commitid^ (to remove last commit from local repository)
	git commit --amend -m "new commit message" (to rename the commit message)
