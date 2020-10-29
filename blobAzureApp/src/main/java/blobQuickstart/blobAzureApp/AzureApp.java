// MIT License
// Copyright (c) Microsoft Corporation. All rights reserved.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE

package blobQuickstart.blobAzureApp;


import bean.*;
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URISyntaxException;

/* *************************************************************************************************************************
* Summary: This application demonstrates how to use the Blob Storage service.
* It does so by creating a container, creating a file, then uploading that file, listing all files in a container, 
* and downloading the file. Then it deletes all the resources it created
* 
* Documentation References:
* Associated Article - https://docs.microsoft.com/en-us/azure/storage/blobs/storage-quickstart-blobs-java
* What is a Storage Account - http://azure.microsoft.com/en-us/documentation/articles/storage-whatis-account/
* Getting Started with Blobs - http://azure.microsoft.com/en-us/documentation/articles/storage-dotnet-how-to-use-blobs/
* Blob Service Concepts - http://msdn.microsoft.com/en-us/library/dd179376.aspx 
* Blob Service REST API - http://msdn.microsoft.com/en-us/library/dd135733.aspx
* *************************************************************************************************************************
*/
public class AzureApp 
{
	/* *************************************************************************************************************************
	* Instructions: Start an Azure storage emulator, such as Azurite, before running the app.
	*    Alternatively, remove the "UseDevelopmentStorage=true;"; string and uncomment the 3 commented lines.
	*    Then, update the storageConnectionString variable with your AccountName and Key and run the sample.
	* *************************************************************************************************************************
	*/
	private static final String ROOT_FOLDER = "root folder";

	public static final String accountName = "rztibloblstoragetest";
	public static final String accountKey = "4c1uewpVdHwNGzx+7ZyXEdbBUbDXcm5ymj6oa1BbFV+X1I5Qqsc2oldZI02HabSDzhDj80rkmHATzUUMR+i9cw==";

	public static final String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=rztibloblstoragetest;AccountKey=" +
			"4c1uewpVdHwNGzx+7ZyXEdbBUbDXcm5ymj6oa1BbFV+X1I5Qqsc2oldZI02HabSDzhDj80rkmHATzUUMR+i9cw==;EndpointSuffix=core.windows.net";

	//"DefaultEndpointsProtocol=https;" +
	//"AccountName=<account-name>;" +
	//"AccountKey=<account-key>";

/*
	public static void main( String[] args )
	{
		File sourceFile = null, downloadedFile = null;
		System.out.println("Azure Blob storage quick start sample...");

		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container = null;

		try
		{
			// Parse the connection string and create a blob client to interact with Blob storage
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			blobClient = storageAccount.createCloudBlobClient();
			container = blobClient.getContainerReference("quickstartcontainer");

			// Create the container if it does not exist with public access.
			System.out.println("Creating container: " + container.getName());
			container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());

			// Creating a sample directory
			File files = new File("Directory");
			files.mkdir();

			// Create empty content
			InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

			// Creating a sample file
			sourceFile = File.createTempFile("sampleFile", ".txt");
			System.out.println("Creating a sample file at: " + sourceFile.toString());
			Writer output = new BufferedWriter(new FileWriter(sourceFile));
			output.write("Hello Azure!");
			output.close();

			// Getting a blob reference
			CloudBlockBlob blob = container.getBlockBlobReference(files+"/"+sourceFile.getName());

			blob.upload(emptyContent,0);

			blob.deleteIfExists();

			//Creating blob and uploading file to it
			System.out.println("Uploading the sample file ");
			blob.uploadFromFile(sourceFile.getAbsolutePath());

			//Listing contents of container
			for (ListBlobItem blobItem : container.listBlobs(files+"/"))
			{
				CloudBlockBlob srcBlob = container.getBlockBlobReference(blobItem.getUri().getPath());
				System.out.println(srcBlob.getName());
		    }

		    // Download blob. In most cases, you would have to retrieve the reference
		    // to cloudBlockBlob here. However, we created that reference earlier, and
		    // haven't changed the blob we're interested in, so we can reuse it.
			// Here we are creating a new file to download to. Alternatively you can also
			// pass in the path as a string into downloadToFile method: blob.downloadToFile("/path/to/new/file").

		downloadedFile = new File(sourceFile.getParentFile(), "downloadedFile.txt");
		blob.downloadToFile(downloadedFile.getAbsolutePath());

		blob.deleteIfExists();
		}
		catch (StorageException ex)
		{
			System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
		}
		catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
		}
		finally 
		{
			System.out.println("The program has completed successfully.");
			System.out.println("Press the 'Enter' key while in the console to delete the sample files, example container, and exit the application.");

			//Pausing for input
			Scanner sc = new Scanner(System.in);
			sc.nextLine();

			System.out.println("Deleting the container");
			try {
				if(container != null)
					container.deleteIfExists();
			}
			catch (StorageException ex) {
				System.out.println(String.format("Service error. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
			}

			System.out.println("Deleting the source, and downloaded files");

			if(downloadedFile != null)
				downloadedFile.deleteOnExit();

			if(sourceFile != null)
				sourceFile.deleteOnExit();

			//Closing scanner
			sc.close();
		}
	}*/


	public static void main( String[] args )
	{
		File sourceFile = null, downloadedFile = null;
		System.out.println("Azure Blob storage quick start sample...");

		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container = null;

		try
		{
			// Parse the connection string and create a blob client to interact with Blob storage
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			blobClient = storageAccount.createCloudBlobClient();
			container = blobClient.getContainerReference("quickstartcontainer");

			// Create the container if it does not exist with public access.
			System.out.println("Creating container: " + container.getName());
			container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());

			// Creating a sample file
//			sourceFile = File.createTempFile("sampleFile", ".txt");
//			System.out.println("Creating a sample file at: " + sourceFile.toString());
//			Writer output = new BufferedWriter(new FileWriter(sourceFile));
//			output.write("Hello Azure!");
//			output.close();

//			CreateBlobRequest createBlobRequest = new CreateBlobRequest();
//
//			createBlobRequest.setName("TestingFile");
//			createBlobRequest.setContainerName("quickstartcontainer");
//			createBlobRequest.setParentPath("/");
//			createBlobRequest.setBlob(true);
//			createBlobRequest.setFileDelimiter("/");
//			createBlobRequest.setStartIndex("0");
//			createBlobRequest.setMaxKeys(10);

//			createFolder(createBlobRequest, container);

//			UploadFileRequest uploadFileRequest = new UploadFileRequest();
//
//			uploadFileRequest.setFile(sourceFile);
//			uploadFileRequest.setInputStream(null);
//			uploadFileRequest.setName("Test2");
//			uploadFileRequest.setContainerName("quickstartcontainer");
//			uploadFileRequest.setParentPath("TestFolder/");
//			uploadFileRequest.setBlob(true);
//			uploadFileRequest.setFileDelimiter("/");
//			uploadFileRequest.setStartIndex("0");
//			uploadFileRequest.setMaxKeys(10);

//			uploadFile(container, uploadFileRequest);

//			CreateBlobRequest downloadRequest = new CreateBlobRequest();
//
//			downloadRequest.setName("TestAgain");
//			downloadRequest.setContainerName("quickstartcontainer");
//			downloadRequest.setParentPath("/");
//			downloadRequest.setBlob(true);
//			downloadRequest.setFileDelimiter("/");
//			downloadRequest.setStartIndex("0");
//			downloadRequest.setMaxKeys(10);

//			downloadFile(container, downloadRequest);

			CreateBlobRequest deleteFileRequest = new CreateBlobRequest();

			deleteFileRequest.setName("TestingFile");
			deleteFileRequest.setContainerName("quickstartcontainer");
			deleteFileRequest.setParentPath("TestingFile/");
			deleteFileRequest.setBlob(true);
			deleteFileRequest.setFileDelimiter("/");
			deleteFileRequest.setStartIndex("0");
			deleteFileRequest.setMaxKeys(10);

			deleteFile(container, deleteFileRequest);
		}

		catch (StorageException ex)
		{
			System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s", ex.getHttpStatusCode(), ex.getErrorCode()));
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public static void createFolder(CreateBlobRequest createBlobRequest, CloudBlobContainer cloudBlobContainer)
			throws IOException, DataConnectorException, StorageException, URISyntaxException
	{
		if( !cloudBlobContainer.exists() )
			throw new DataConnectorException("Container not present !");

		if( !FilePathUtil.isValidFileName(createBlobRequest.getName()) )
			throw new DataConnectorException("Not a valid file/folder name");

		if( isExists(cloudBlobContainer,
				new CreateBlobRequest(createBlobRequest.getName(), createBlobRequest.getParentPath(), createBlobRequest.getContainerName(),
						createBlobRequest.isBlob())) )
		{
			throw new DataConnectorException("File/Folder already exist with name " + createBlobRequest.getName() + " in " + (StringUtils
					.isEmpty(createBlobRequest.getParentPath()) ? ROOT_FOLDER : createBlobRequest.getParentPath()) + " .");
		}

		// Create empty content...

		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

		String parentPath, folderName;

		if(createBlobRequest.isBlob())
		{
			parentPath = FilePathUtil.appendSlashIfNot(createBlobRequest.getParentPath());
			folderName = FilePathUtil.appendSlashIfNot(createBlobRequest.getName());
		}
		else
		{
			parentPath = FilePathUtil.appendSlashIfNot(createBlobRequest.getParentPath());
			folderName = FilePathUtil.removeSlashIfEndsWith(createBlobRequest.getName());
		}

		CloudBlockBlob blob = cloudBlobContainer.getBlockBlobReference(parentPath+ folderName +
				File.separator +emptyContent);

		blob.upload(emptyContent,0);

		// Removing the empty content...

		blob.deleteIfExists();

		CreateFolderResponse response = new CreateFolderResponse();

		response.setFolderName(createBlobRequest.getName());
		response.setAbsolutePath(createBlobRequest.getParentPath() + File.separator + createBlobRequest.getName());
		response.setParentPath(createBlobRequest.getParentPath());
	}

	public static boolean isExists(CloudBlobContainer cloudBlobContainer,CreateBlobRequest folderExistsRequest)
			throws DataConnectorException {
		try
		{
			CloudBlockBlob blob;

			String prefix = folderExistsRequest.getParentPath();

			if( folderExistsRequest.getParentPath().startsWith(File.separator) )
			{
				prefix = folderExistsRequest.getParentPath().substring(1);
			}
			if( !prefix.isEmpty() )
			{
				prefix = prefix + File.separator;
			}
			if( folderExistsRequest.isBlob() )
			{
				blob = cloudBlobContainer.getBlockBlobReference(prefix + folderExistsRequest.getName());
			}
			else
			{
				blob = cloudBlobContainer.getBlockBlobReference(
						prefix + FilePathUtil.removeSlashIfEndsWith(folderExistsRequest.getName()));
			}
			return (blob.exists());
		}
		catch( Exception e )
		{
			throw new DataConnectorException(ExceptionMessageUtil.parseMessage(e.getMessage()), e);
		}
	}

	public static void uploadFile(CloudBlobContainer cloudBlobContainer, UploadFileRequest uploadFileRequest)
			throws DataConnectorException
	{
		try
		{
			if( isExists(cloudBlobContainer, new CreateBlobRequest(uploadFileRequest.getName(), uploadFileRequest.getParentPath(), uploadFileRequest.getContainerName(),
					false)) )
			{
				throw new DataConnectorException("File " + uploadFileRequest.getName() + " already exist in " +
						(StringUtils.isEmpty(uploadFileRequest.getParentPath()) ? ROOT_FOLDER : uploadFileRequest.getParentPath()) + " .");
			}
			FilePathUtil.isValidFileName(uploadFileRequest.getFileName());

			CloudBlob cloudBlob = cloudBlobContainer.getBlockBlobReference(uploadFileRequest.getParentPath()+File.separator+uploadFileRequest.getFileName());

			if( uploadFileRequest.getInputStream() != null )
			{
				cloudBlob.upload(uploadFileRequest.getInputStream(), IOUtils.toByteArray(uploadFileRequest.getInputStream()).length);

			}
			else if( uploadFileRequest.getFile() != null )
			{
				cloudBlob.uploadFromFile(uploadFileRequest.getFile().getAbsolutePath());
			}
			else
			{
				throw new DataConnectorException("Please provide file or input stream to upload");
			}
			CreateFolderResponse response = new CreateFolderResponse();

			response.setFolderName(uploadFileRequest.getName());
			response.setParentPath(uploadFileRequest.getParentPath());
			response.setAbsolutePath(
					uploadFileRequest.getContainerName() + File.separator + uploadFileRequest.getParentPath()
							+ File.separator + uploadFileRequest.getName());
		}
		catch( Exception e )
		{
			throw new DataConnectorException(ExceptionMessageUtil.parseMessage(e.getMessage()), e);
		}
	}

	public static void downloadFile(CloudBlobContainer cloudBlobContainer, CreateBlobRequest downloadRequest)
			throws DataConnectorException
	{
		try
		{
			isExists(cloudBlobContainer, new CreateBlobRequest(downloadRequest.getName(), downloadRequest.getParentPath(),
					downloadRequest.getContainerName(), downloadRequest.isBlob()));

			CloudBlob cloudBlob = cloudBlobContainer.getBlockBlobReference(
					FilePathUtil.appendSlashIfNot(downloadRequest.getParentPath()) +
							 FilePathUtil.removeSlashIfEndsWith(downloadRequest.getName()));

			int length = (int) cloudBlob.getProperties().getLength();

			System.out.println(length);

			byte[] file = new byte[length];

			cloudBlob.downloadToByteArray(file, 0);
		}
		catch( Exception e )
		{
			throw new DataConnectorException("Sorry! Something went wrong while reading the file content");
		}
	}

	public static void deleteFile(CloudBlobContainer cloudBlobContainer, CreateBlobRequest deleteFileRequest)
			throws StorageException {
		try
		{
			if( deleteFileRequest.isBlob() )
			{
				deleteFileRecursively(cloudBlobContainer, deleteFileRequest);

				cloudBlobContainer.getBlockBlobReference(FilePathUtil.removeSlashIfEndsWith(deleteFileRequest.getName()))
						.deleteIfExists();
			}
			else
			{
				cloudBlobContainer.getBlockBlobReference(deleteFileRequest.getName()).deleteIfExists();
			}
		}
		catch(DataConnectorException | URISyntaxException e )
		{
			System.out.println(e.getMessage());
		}
	}

	private static void deleteFileRecursively( CloudBlobContainer cloudBlobContainer, CreateBlobRequest deleteFileRequest )
			throws DataConnectorException
	{
		try
		{
			if( deleteFileRequest.isBlob() )
			{
				String fileDelimiter = "/";
				String prefix = deleteFileRequest.getParentPath();

				if( deleteFileRequest.getParentPath().startsWith(File.separator) )
				{
					prefix = deleteFileRequest.getParentPath().substring(1);
				}

				if( !deleteFileRequest.getParentPath().isEmpty() && !deleteFileRequest.getParentPath()
						.endsWith(fileDelimiter) )
				{
					prefix += fileDelimiter;
				}

				for (ListBlobItem blob : cloudBlobContainer.listBlobs(prefix))
				{
					CloudBlockBlob srcBlob = cloudBlobContainer.getBlockBlobReference(blob.getUri().getPath());

					System.out.println(srcBlob.exists());

					System.out.println(srcBlob.getName());

					System.out.println(srcBlob.deleteIfExists());
				}
			}
		}
		catch( Exception e )
		{
			throw new DataConnectorException(ExceptionMessageUtil.parseMessage(e.getMessage()), e);
		}
	}
}