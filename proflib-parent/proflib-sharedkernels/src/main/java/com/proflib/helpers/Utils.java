package com.proflib.helpers;

import com.github.javafaker.Faker;
import jakarta.enterprise.event.Event;
import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.*;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Utils {
    static final org.slf4j.Logger log = LoggerFactory.getLogger(Utils.class);
    public static final String DEBUG_LOG = "DEBUG parameter {} result: {}";
    public static final String PAS_DE_FICHIER_A_CONVERTIR_LOG = "pas de fichier a convertir  ";
    public static final String LA_CONVERSION_A_PRIS_TROP_DE_TEMPS_LOG = "la conversion a pris trop de temps ";
    public static final String FFMPEG_I_CMD = "ffmpeg -i ";
    private String[] FFMPEG_I_CMD_TABLE={"ffmpeg","-i"};

    public static final String UTF_8 = "UTF-8";
    public static final String PAS_PU_SUPPRIMER_LOG = "pas pu supprimer ";

    Jsonb jsonb = JsonbBuilder.create();
    public static void fireEventHidingException(Event<String> event, String msg) {
        try {
            event.fire(msg);
        } catch (Exception e) {
            log.error("SSE- fired event returned exception {}",e.getMessage());
        }
    }

    public static String generateFakePseudo() {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        return name;
    }

    public Comparator<? super String> getExtrSecComparator() {
        Utils utils = this;
        return new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                String[] ext_sec1 = utils.getExtraitSecondFromFileName(s);
                String[] extr_sec2 = utils.getExtraitSecondFromFileName(t1);
                int extr1 = Integer.parseInt(ext_sec1[0]);
                int extr2 = Integer.parseInt(extr_sec2[0]);
                int sec1 = Integer.parseInt(ext_sec1[1]);
                int sec2 = Integer.parseInt(extr_sec2[1]);
                if (extr1 > extr2) return 1;
                else if (extr1 < extr2) return -1;
                else if (sec1>sec2) return 1;
                else if (sec1<sec2) return -1;
                else return 0;
            }
        };
    }

    public boolean fileIsExtrait(File file) {
        return (!file.isDirectory() && getFileExtension(file.getName()).equals(""));
    }

    public boolean hasFormatFileUndescoreSecond(String fileName) {
        String[] extraitSecond = getExtraitSecondFromFileName(removeFileExtension(fileName));
        return extraitSecond.length == 2 && extraitSecond[0].matches("\\d+") && extraitSecond[1].matches("\\d+");
    }

    public String[] getExtraitSecondFromFileName(String fileName) {
        return removeFileExtension(fileName).split("_");
    }

    public File changeFromStereoWaveToMonoWaveWithFFMpeg(String wavePath, String outPath) throws IOException, InterruptedException {
        String command = FFMPEG_I_CMD + wavePath + " -ac 1 " + outPath;
        log.debug(command);
        File convertedFile = new File(outPath);
        if (convertedFile.exists()) convertedFile.delete();
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command).waitFor();
        return convertedFile;
    }
    public ArrayList convertToJsonArray(String jsonString) {

        return jsonb.fromJson(jsonString, ArrayList.class);
    }
    public String convertJsonArrayToString(List<String> fichierNamesString) {
        return jsonb.toJson(fichierNamesString);
    }
    public Jsonb getJsonb() {
        return jsonb;
    }
    private static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd-HH-mm-ss";
    String initVector="encryptionIntVec";

    public Utils()
    {

    }

    public static void saveInputStreamIntoPath(InputStream inputStream, String pathWhereToSave) throws IOException
    {
        try (FileOutputStream out = new FileOutputStream(pathWhereToSave)) {
            int data = inputStream.read();
            while (data != -1) {
                out.write(data);
                data = inputStream.read();
            }
        }
        inputStream.close();
    }

    public static String replaceFileExtention(String fileName, String newExtensionWithPoint) {
        String previousFileExtension = getFileExtension(fileName);
        if (previousFileExtension.equals(newExtensionWithPoint)) return fileName;
        int indexOfExtension = fileName.lastIndexOf(previousFileExtension);
        return fileName.substring(0,indexOfExtension).concat(newExtensionWithPoint);


    }

    public String saveInputStreamToFileWithRecursiveDirCreationIfNeeded(InputStream inputStream, String uploadPathWithEndingSlash, String fileName) throws Exception
    {
        String fullUploadPath = uploadPathWithEndingSlash.concat(fileName);
        log.debug("save into {}",Paths.get(fullUploadPath));
        File uploadDir = new File(uploadPathWithEndingSlash);

        if (!uploadDir.exists()) {
            if (!uploadDir.mkdirs()) throw new Exception("Le repertoire : "+uploadPathWithEndingSlash+ " n'a pas pu être créee");
        }
        Files.copy(inputStream,Paths.get(fullUploadPath));
        return fullUploadPath;
    }

    //TODO: corriger ne compte pas les retour chariot
    public int countWordInFile(String textFilePath) throws Exception
    {
        String text = getStringFromFile(new File(textFilePath));
        return countWordInString(text);

    }

    public int countWordInString(String text) {
        int nombreRetourChariot= text.split("\n").length;
        int nombreespaces = text.split(" ").length;
        return nombreespaces+nombreRetourChariot;
    }

    public static String getDateFormat(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date.getTime());
    }


    public String generateNewTokenEmailValidation()
    {
        boolean foundavailableemailtoken=false;
        String emailtoken= UUID.randomUUID().toString();
        return emailtoken;
    }



    public void printStacktrace(String label) {
        StackTraceElement[] stck = Thread.currentThread().getStackTrace();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        pw.write("PrintStackTrace "+label+": \n");
        for (int i=0; i<stck.length; i++)
        {
            pw.write(stck[i].getClassName()+":"+stck[i].getMethodName()+"\n");
        }
        pw.flush();
        sw.flush(); //Flushes this output stream and forces any buffered output bytes to be written out.
        log.debug(sw.toString());
    }

    public boolean copyFileToDirectory(String srcFilePath, String dstDirPathWithEndingSlash)  {
        final String fileName = new File(srcFilePath).getName();
        return copyFile(srcFilePath,dstDirPathWithEndingSlash.concat(fileName));
    }
    public boolean copyFile(String srcFilePath, String dstFilePath) {
        log.debug("copie: "+srcFilePath+" vers "+dstFilePath);
        boolean isDone = false;

        try {
            final FileOutputStream fileOutputStream;
            FileChannel dstChannel;
            try (FileChannel srcChannel = new FileInputStream(srcFilePath).getChannel()) {
                if (!new File(dstFilePath).getParentFile().exists()) {
                    throw new FileNotFoundException("Le repertoire de destination n'existe pas: " + dstFilePath);
                }
                fileOutputStream = new FileOutputStream(dstFilePath);
                dstChannel = fileOutputStream.getChannel();
                dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
           }
            fileOutputStream.flush();
            dstChannel.close();
            isDone=true;
        } catch (IOException e) {
            log.warn("erreur copie",e);
        }

        return isDone;
    }
    public boolean copyDirectory(File src, File dst) throws Exception {
        FileUtils.copyDirectory(src, dst);
        return true;
    }

    public boolean filePathExist(String path)
    {
        File totest = new File(path);
        return totest.exists();
    }

    public boolean deleteFile(String filePath)
    {
        File todelete= new File(filePath);
        if (!todelete.exists()) return true;
        boolean deleted = todelete.delete();
        if (!deleted) log.warn("fichier {} n'a pas pu etre supprime",filePath);
        return deleted;
    }

    public void preparedStatementFriendly(String query, String[] params)
    {
        String[] tablequery = query.split("\\?");
        StringBuffer res = new StringBuffer();
        for (int i=0; i<tablequery.length; i++)
        {
            res.append(tablequery[i]+params[i]);
        }
        log.info(res.toString());
    }

    public static String removeFileExtension(String filename)
    {
        if (filename.lastIndexOf(".")==-1) return filename;
        return filename.substring(0,filename.lastIndexOf("."));
    }

    public static String getFileExtension(String filename) {
        if (filename.lastIndexOf(".")==-1) return "";
        return filename.substring(filename.lastIndexOf("."));
    }
    public String readFileContent(File file) throws IOException
    {
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(bytes, UTF_8);
    }


    private BufferedInputStream fileToBufferedInputstream(File source) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(source));
    }

    public String removeSpaces(String fileName) {
        return fileName.replaceAll("\\s","");
    }

    private void moveFileWithFallBackAsCopyAndDeleteIfProblem(File source, File dest) throws Exception {
        if (!source.renameTo(dest))
        {
             if (copyFile(source.getAbsolutePath(), dest.getAbsolutePath())==false) {
                 throw new Exception("pas pu renommer de " + source.getAbsolutePath() + " vers " + dest.getAbsolutePath());
             }
            try {
                deleteFile(source.getAbsolutePath());
            }
             catch(Exception e) {
                log.warn(PAS_PU_SUPPRIMER_LOG + source.getAbsolutePath());
            }
     }
    }

    public String generateUsableFileNameWithTempPath(String prefix, Integer max, String ext) throws IOException {
        final String tempDirectoryPath = "TODO:mettreunpathenparam";
        log.debug(tempDirectoryPath);
        File tempDirectory = new File(tempDirectoryPath);
        if (!tempDirectory.exists()){
            FileUtils.forceMkdir(tempDirectory);
        }
        String filename = generateSpaceFreeFileName(prefix, max, ext);
        return tempDirectoryPath + filename;
    }
    public String generateSpaceFreeFileName(String prefix, Integer maxOfBeforeExt, String ext)  {

        String randomPart = UUID.randomUUID().toString() + Long.toString(Calendar.getInstance().getTime().getTime());
        String nomFichier= prefix + randomPart + ext;
        if(nomFichier.length()-ext.length()>maxOfBeforeExt)
        {
            int midsize = maxOfBeforeExt / 2;
            String midRandomPart = randomPart.length()>midsize?randomPart.substring(0, midsize):randomPart;
            nomFichier=nomFichier.substring(0,midsize)+ midRandomPart +ext;
        }
        File test= new File( "TODO:mettreunpathenparam"+nomFichier);
        while(test.exists())
        {
            log.info(nomFichier +" existe deja recherche autre nom...");
            nomFichier= prefix+ UUID.randomUUID().toString()+Long.toString(Calendar.getInstance().getTime().getTime())+ext;
            test= new File( "TODO:mettreunpathenparam"+nomFichier);
            log.info(nomFichier +" existe deja recherche autre nom...");
        }
        return nomFichier;
    }
    public String generateSpaceFreeFileName(String prefix, String extension)
    {
        return this.removeSpaces(prefix).concat(UUID.randomUUID().toString()).concat(this.removeSpaces(extension));
    }
    public String getHeaderCaseInsensitive(HttpServletRequest request, String parameter)
    {
        String modifiedParam=parameter;
        String result=request.getHeader(modifiedParam);
//        log.debug(DEBUG_LOG,modifiedParam,result);
        if (result!=null) return result;

        modifiedParam=parameter.toLowerCase();
        result=request.getHeader(modifiedParam);
//        log.debug(DEBUG_LOG,modifiedParam,result);
        if (result!=null) return result;

        modifiedParam=convertToCamelcase(parameter);
        result=request.getHeader(modifiedParam);
 //       log.debug(DEBUG_LOG,modifiedParam,result);
        if (result!=null) return result;

        modifiedParam=parameter.toUpperCase();
        result=request.getHeader(modifiedParam);
 //       log.debug(DEBUG_LOG,modifiedParam,result);
        if (result!=null) return result;
        return null;
    }

    public String convertToCamelcase(String test)
    {
        return test.substring(0,1).toUpperCase().concat(test.substring(1));
    }

    public byte[] aesSymetricEncrypt(File aesJksKey, String keyalias, String keypassword, String valeurACrypterDecrypter) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException, KeyStoreException, CertificateException, UnrecoverableKeyException {
        //TODO: ne plus utiliser le meme iv tout le temps
        KeyStore keystore = KeyStore.getInstance("JCEKS");
        keystore.load( new FileInputStream(aesJksKey),keypassword.toCharArray());
        SecretKey privateKey = (SecretKey) keystore.getKey(keyalias, keypassword.toCharArray());

        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes());
        SecretKeySpec skeySpec = new SecretKeySpec(privateKey.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(valeurACrypterDecrypter.getBytes());
        byte[] res = Base64.getEncoder().encode(encrypted);
        return res;
    }

    public String aesSymetriqueDecrypt(File asymkey, String aliaskey, String passwordkey, String encryptedString) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, KeyStoreException, CertificateException, UnrecoverableKeyException {
            //TODO: ne plus utiliser le meme iv tout le temps
            KeyStore keystore = KeyStore.getInstance("JCEKS");
            keystore.load( new FileInputStream(asymkey),passwordkey.toCharArray());
            SecretKey privateKey = (SecretKey) keystore.getKey(aliaskey, passwordkey.toCharArray());

        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF_8));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");//TODO: a remplacer par ce qui est compliant ajourd'hui
            SecretKeySpec skeySpec = new SecretKeySpec(privateKey.getEncoded(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedString.getBytes()));
            return new String(original);

    }
    public String generateEmailAssistanceTicket() {
        SecureRandom random = new SecureRandom();
        int inte = random.nextInt(1000000);
        String nombre = Integer.toString(inte);
        return nombre;
    }

    public String hashSha256(String ahasher) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hashbytes = digest.digest(
                ahasher.getBytes(StandardCharsets.UTF_8));
        String sha3_256hex = bytesToHex(hashbytes);
        return sha3_256hex;
    }
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void compressJpgFile(String jpegAbsolutePath) throws Exception {
        ProcessBuilder pb =new ProcessBuilder("jpegoptim", "-m 30", jpegAbsolutePath);
        Process p = pb.start();
        log.debug(this.getStringFromInputStream(p.getInputStream(), UTF_8));
    }

    public void createDeepDirectoryIfDontExist(String projectTempPath) {
        File dirToCreate = new File(projectTempPath);
        if ( !dirToCreate.exists() && !dirToCreate.isDirectory() )
            dirToCreate.mkdirs();
        }

    public List<?> increaseArraySizeTo(List<?> tableToIncrease, Integer size) {
        Object[] stringArray = (Object[])tableToIncrease.toArray(new Object[tableToIncrease.size()]);
        List<Object> objects = Arrays.asList(Arrays.copyOf(stringArray, size));
        return objects;
    }

    public boolean isANumberEntier(String name) {
            boolean ret = true;
        for(int i=0; i<name.length(); i++)
        {if (!Character.isDigit(name.charAt(i))) return false;}
            return ret;
        }

    public String sendRequest(String urlToUpload) throws IOException, InterruptedException {
        final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(urlToUpload)).setHeader("User-Agent", "Java 11 HttpClient Bot").build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().isEmpty()) throw new IOException("404 empty");
        return response.body();
    }

    public void writeBytesInFile(Path filePath, byte[] content) throws IOException {
        Files.write(filePath, content);
    }

    public static String replaceHtmlAndScriptCharWithSafeChar(String pastedText) {
        return pastedText.replace("script", "s.cript").replace(">","&gt;").replace("<","&lt;");
    }
    public static String toCamelCase(String valeur) {
        return Character.toUpperCase(valeur.charAt(0))+valeur.substring(1);
    }
    public String extractFileNameFromContentDispositionHeader(String contentDisposition)
    {
        final String token = "; filename=\"";
        return contentDisposition.substring(contentDisposition.indexOf(token)+token.length(),contentDisposition.length()-1);
    }
    public  boolean deleteDir(File dir)
    {
        File[] files = dir.listFiles();
        if (files != null) { // ou files.lenght >0?
            for (File file : files) {
                boolean deleted = file.delete(); // voir supprimer un fichier
                if (!deleted) {
                    // directory
                    if (file.isDirectory()) deleteDir(file);
                    // otherwise, there's nothing else we can do
                }
            }
        }
        return dir.delete(); // voir supprimer un fichier
    }/*
    public  boolean deleteDirExcept(File dir, String[] extensions)
    {
        File[] files = dir.listFiles();
        if (files != null) { // ou files.lenght >0?
            for (File file : files)
            {
                boolean godelete = true;
                for (int i=0; i<extensions.length; i++)
                {
                    if (file.getName().endsWith(extensions[i]))
                    {
                        godelete=false;
                        break;
                    }
                }
                if (godelete)
                {
                    boolean deleted = file.delete(); // voir supprimer un fichier
                    if (!deleted)
                    {
                        // directory
                        if (file.isDirectory()) deleteDirExcept(file, extensions);
                        // otherwise, there's nothing else we can do
                    }
                }
            }
        }
        boolean godelete = true;
        boolean ret = true;
        for (int i=0; i<extensions.length; i++)
        {
            if (dir.getName().endsWith(extensions[i]))
            {
                godelete=false;
                break;
            }
        }
        if (godelete)
        {
            ret = dir.delete();
        }
        return ret;
    } */
    public boolean deleteDirOnlyExtAndOld(File dir, String[] extensions, long millisecondold)
    {
        File[] files = dir.listFiles();
        if (files != null)
        { // ou files.lenght >0?
            for (File file : files)
            {
                boolean godelete=false;
                for (int i=0; i<extensions.length; i++)
                {
                    if (file.getName().contains(extensions[i]))
                    {
                        log.info("fichier "+file.getName()+" d'extension "+extensions[i]+" est vieux de "+file.lastModified()+" on le supprime?");
                        log.info(new Date().getTime()-file.lastModified()+" > "+millisecondold+"?");
                        if (new Date().getTime()-file.lastModified()>millisecondold)
                        {
                            log.info("on le supprime!");
                            godelete=true;
                            break;
                        }
                    }
                }
                if (godelete)
                {
                    boolean deleted = file.delete(); // voir supprimer un fichier
                    if (!deleted) {
                        // directory
                        if (file.isDirectory()) deleteDir(file);
                        // otherwise, there's nothing else we can do
                    }
                }
            }
        }
        boolean godelete=false;
        boolean ret=true;
        for (int i=0; i<extensions.length; i++)
        {
            if (dir.getName().contains(extensions[i]))
            {
                log.info("fichier "+dir.getName()+" d'extension "+extensions[i]+" est vieux de "+dir.lastModified()+" on le supprime?");
                if (new Date().getTime()-dir.lastModified()>millisecondold)
                {
                    log.info("on le supprime!");
                    godelete=true;
                    break;
                }
            }
        }
        if (godelete)
        {
            ret = dir.delete();
        }
        return ret;
    }

    public  String getStringFromInputStream(InputStream inputStream, String code) throws Exception
    {
        ByteArrayOutputStream  lByteOut = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        InputStreamReader reader = new InputStreamReader(inputStream, code);
        //BufferedInputStream reader = new BufferedInputStream(lire);
        int i;
        while((i=inputStream.read(bytes, 0, bytes.length))>-1) {
            lByteOut.write(bytes, 0, i);
        }
        inputStream.close();
        reader.close();
        return lByteOut.toString();
    }
    public  String getStringFromFileEncoded(File file, String code) throws Exception
    {
        ByteArrayOutputStream  lByteOut = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        try(FileInputStream lire = new FileInputStream(file)) {
            InputStreamReader reader = new InputStreamReader(lire, code);
            int i;
            while ((i = lire.read(bytes, 0, bytes.length)) > -1) {
                lByteOut.write(bytes, 0, i);
            }
            reader.close();
            return lByteOut.toString();
        }
    }
/*
    public  boolean containsTwoMajAligned(String tok) throws Exception
    {
        boolean enMaj = false;
        for(int i = 0; i < tok.length(); i++)
        {
            if(Character.isUpperCase(tok.charAt(i)) && enMaj==true)
            {
                break;
            }
            if(Character.isUpperCase(tok.charAt(i)))
            {
                enMaj = true;
            }
            else
            {
                enMaj=false;
            }
        }
        return enMaj;
    }



*/

    public   List<String> linkedHashMapToList(LinkedHashMap<Double,String> value) throws Exception
    {
        List<String> ret = new ArrayList<String>(value.size());
        Iterator<Map.Entry<Double, String>> entryIterator = value.entrySet().iterator();
        while (entryIterator.hasNext())
        {
            ret.add(entryIterator.next().getValue());
        }
        return ret;

    }

    public    LinkedHashMap<Integer,String> listToLinkedHashMap(List<String> value) throws Exception
    {
        LinkedHashMap<Integer,String> ret = new LinkedHashMap<Integer,String>(value.size());
        for (int i=0; i<value.size(); i++)
        {
            ret.put(i,value.get(i));
        }
        return ret;

    }
    /*
    public  boolean copyFile(String srcPath, String dstPath) throws Exception {
        boolean isDone = false;

        // Create channel on the source
        FileChannel srcChannel = new FileInputStream(srcPath).getChannel();

        // Create channel on the destination
        FileChannel dstChannel = new FileOutputStream(dstPath).getChannel();

        // Copy file contents from source to destination
        dstChannel.transferFrom(srcChannel, 0, srcChannel.size());

        // Close the channels
        srcChannel.close();
        dstChannel.close();
        isDone=true;
        return isDone;
    }
    *//**
     * @param date Java Date instance
     * @return return a formatted date following MySql pattern
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getMySqlDateFormat(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat(MYSQL_DATE_FORMAT);
        String strDate ="";

        // Using Calendar as Date methodes are  deprecated
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        strDate = sdf.format(calendar.getTime());

        return strDate;
    }
    public  boolean copier( File source, File destination ) throws Exception{ //Methode permettant la copie d'un fichier
        log.debug("copie: "+source+" vers "+destination);
        boolean resultat = false;

        // Declaration des flux
        FileInputStream sourceFile=null;
        FileOutputStream destinationFile=null;
        try {
            // Cr�ation du fichier :
            log.debug(destination.getAbsolutePath());
            destination.createNewFile();
            // Ouverture des flux
            sourceFile = new FileInputStream(source);
            destinationFile = new FileOutputStream(destination);
            // Lecture par segment de 0.5Mo
            byte buffer[]=new byte[512*1024];
            int nbLecture;
            while( (nbLecture = sourceFile.read(buffer)) != -1 ) {
                destinationFile.write(buffer, 0, nbLecture);
            }

            // Copie r�ussie
            resultat = true;
        } catch( IOException e )
        {
            log.error("cannot copy "+source.getAbsolutePath()+ " to "+ destination.getAbsolutePath());
            throw e;
        }
         finally {
            // Quoi qu'il arrive, on ferme les flux
            try {
                sourceFile.close();
            } catch(Exception e) { }
            try {
                destinationFile.close();
            } catch(Exception e) { }
        }
        return( resultat );
    }
    /*
    public  int getNbCharFromFile(File file) throws Exception
    {
        int nb=0;
        ByteArrayOutputStream  lByteOut = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        FileInputStream lire = new FileInputStream(file); // fichier est un File
        BufferedInputStream reader = new BufferedInputStream(lire);
        int i;
        while((i=lire.read(bytes, 0, bytes.length))>-1) {

            lByteOut.write(bytes, 0, i);
            nb = nb+ lByteOut.toString().length();
            lByteOut = new ByteArrayOutputStream();
        }
        lire.close();
        reader.close();
        return nb;
    }


    public  String replaceSingleQuotesToDoubleQuotes(String string) {
        String ret = "";
        ret=string.replaceAll("''", "DOUBLEQUOTES");
        ret=ret.replaceAll("'", "''");
        ret=ret.replaceAll("DOUBLEQUOTES", "''");
        return ret;
    }

    public  boolean isNextNCharExists(String line, int pos, int nbToken)
    {
        boolean ret=true;
        try
        {
            String test = line.substring(pos, pos+nbToken);
        }
        catch (Exception e)
        {
            ret=false;;
        }
        return ret;
    }

    public  int getKeyByValue(LinkedHashMap<Integer, Object> acteurList, String trim) throws Exception  {
        int ret = -1;
        int tmp = 0;
        Iterator<Integer> it = acteurList.keySet().iterator();
        while (it.hasNext())
        {
            tmp = it.next();
            if (acteurList.get(tmp)!=null && acteurList.get(tmp).toString().equals(trim))
            {
                ret = tmp;
                break;
            }
        }
        return ret;
    }

    public  String convertToASCII(String toTrim) throws Exception
    {
        CharsetEncoder e = Charset.forName("US-ASCII").newEncoder();
        StringBuilder ret = new StringBuilder();
        char[] a = toTrim.toCharArray();
        for(int i =0; i<a.length; i++)
        {
            if (e.canEncode(a[i]))
            {
                ret.append(a[i]);
            }
        }
        return ret.toString().trim();
    }
    public  String convertToUnicode(String toTrim) throws Exception
    {
        StringBuilder ret = new StringBuilder();
        char[] a = toTrim.toCharArray();
        for(int i =0; i<a.length; i++)
        {
            ret.append(Integer.toHexString((int) a[i]).toUpperCase());
        }
        return ret.toString().trim();
    }
    public  boolean isAllUpper(String string, boolean numberIsUpper) throws Exception {
        boolean ret=false;
        for (int i=0; i<string.length(); i++)
        {
            if (Character.isUpperCase(string.charAt(i)))
            {
                ret=true;
            }
            else if (!Character.isLetter(string.charAt(i)) && Character.isDigit(string.charAt(i)) && numberIsUpper)
            {
                ret=true;
            }
            else if (Character.isSpaceChar(string.charAt(i)))
            {}
            else
            {
                ret=false;
                break;
            }
        }

        return ret;
    }

    public  void writeInPathEncoded(String string, String substring, String code) throws Exception {
        //FileWriter fw = new FileWriter (new File(app.getProperty(TEMP_PATH)+AudioLoopMain.getInstance().idProjet+File.separator+string));
        //BufferedWriter bw = new BufferedWriter (fw);
        FileOutputStream fosw = new FileOutputStream(app.getProperty(TEMP_PATH)+AudioLoopMain.getInstance().idProjet+File.separator+string);
        OutputStreamWriter osw =null;
        if (code==null || code.equals("") || code.equals("UTF-8"))
        {
            log.debug("encoding = none");
            osw = new OutputStreamWriter(fosw);
        }
        else
        {
            log.debug("encoding = "+code);
            osw = new OutputStreamWriter(fosw,code);
        }
        try
        {
            osw.write(substring);
        }
        finally
        {
            osw.close();
            fosw.close();
        }
        //PrintWriter fichierSortie = new PrintWriter (bw);
        //fichierSortie.println (substring);
        //fichierSortie.close();
        //fw.close();

    }*/
    public static void writeInFileEncoded(String textToWrite, File fFileName, String code) throws IOException
    {

        //CharsetEncoder isoencoder = Charset.forName("ISO-8859-15").newEncoder();
        Writer out;
        if (code.isEmpty())
        {
            out = new OutputStreamWriter(new FileOutputStream(fFileName));
        }
        else
        {
            out = new OutputStreamWriter(new FileOutputStream(fFileName), code);
        }

        try {
            out.write(textToWrite);
        }
        finally {
            out.close();
        }
    }

    public  String cutline(String ligne, int i) throws Exception
    {
        StringBuffer ret = new StringBuffer();
        try {
            ret.append(ligne);
            int nb=0;
            int nbret=0;
            for (int nbchar=0; nbchar<ligne.length(); nbchar++)
            {
                if (nb>i && ligne.length()>nbchar+2 && (ligne.subSequence(nbchar, nbchar+2).equals(". ") || ligne.subSequence(nbchar, nbchar+2).equals("! ") || ligne.subSequence(nbchar, nbchar+2).equals("? ") || ligne.subSequence(nbchar, nbchar+2).equals("; ") || ligne.subSequence(nbchar, nbchar+2).equals(": ") || ligne.subSequence(nbchar, nbchar+2).equals(", ") ))
                {
                    ret.insert(nbchar+2+nbret,System.getProperty("line.separator"));
                    nb=0;
                    nbret++;
                    nbret++;

                }
                nb++;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
        }
        return ret.toString();
    }


    public  int countcutLine(String ligne, int i)throws Exception
    {
        StringBuffer ret = new StringBuffer();
        int nbret=0;
        try {
            ret.append(ligne);
            int nb=0;
            for (int nbchar=0; nbchar<ligne.length(); nbchar++)
            {
                if (nb>i && ligne.length()>nbchar+2 && (ligne.subSequence(nbchar, nbchar+2).equals(". ") || ligne.subSequence(nbchar, nbchar+2).equals("! ") || ligne.subSequence(nbchar, nbchar+2).equals("? ") || ligne.subSequence(nbchar, nbchar+2).equals("; ")|| ligne.subSequence(nbchar, nbchar+2).equals(": ")|| ligne.subSequence(nbchar, nbchar+2).equals(", ")))
                {
                    nb=0;
                    nbret++;
                }
                nb++;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
        }
        return nbret;
    }

    public  String removeAllNonChar(String string) throws Exception
    {
        StringBuffer ret = new StringBuffer();
        for (int i =0; i<string.length(); i++)
        {
            if (Character.isLetter(string.charAt(i)))
            {
                ret.append(string.charAt(i));
            }
        }
        return ret.toString();
    }

    public  boolean deleteDirExcept(File dir, String[] extensions)
    {
        File[] files = dir.listFiles();
        if (files != null) { // ou files.lenght >0?
            for (File file : files)
            {
                boolean godelete = true;
                for (int i=0; i<extensions.length; i++)
                {
                    if (file.getName().endsWith(extensions[i]))
                    {
                        godelete=false;
                        break;
                    }
                }
                if (godelete)
                {
                    boolean deleted = file.delete(); // voir supprimer un fichier
                    if (!deleted)
                    {
                        // directory
                        if (file.isDirectory()) deleteDirExcept(file, extensions);
                        // otherwise, there's nothing else we can do
                    }
                }
            }
        }
        boolean godelete = true;
        boolean ret = true;
        for (int i=0; i<extensions.length; i++)
        {
            if (dir.getName().endsWith(extensions[i]))
            {
                godelete=false;
                break;
            }
        }
        if (godelete)
        {
            ret = dir.delete();
        }
        return ret;
    }

    public static String getStringFromFile(File file) throws Exception
    {
        ByteArrayOutputStream  lByteOut = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        if (!file.exists())
        {
            return "question removed";
        }
        FileInputStream lire = new FileInputStream(file); // fichier est un File
        try(BufferedInputStream reader = new BufferedInputStream(lire)) {
            int i;
            while ((i = lire.read(bytes, 0, bytes.length)) > -1) {
                lByteOut.write(bytes, 0, i);
            }
            lire.close();
            return lByteOut.toString();
        }
    }
    public void insertIntoLinkedHashMap(LinkedHashMap<Integer, LinkedHashMap<Integer, LinkedHashMap<Double, String>>> listToChange, int position, LinkedHashMap<Integer, LinkedHashMap<Double, String>> toinsert)
    {
        while (listToChange.size()<=position)
        {
            listToChange.put(listToChange.size(),new LinkedHashMap<>());
        }
        Integer lastid = listToChange.keySet().stream().collect(Collectors.toList()).get(listToChange.size()-1);
        for (int i = lastid; i>=position; i--)
        {
            listToChange.put(i+1,listToChange.get(i));
        }
        listToChange.put(position,toinsert);

    }

    public void insertIntoLinkedHashMap(LinkedHashMap<Integer, String> listToChange, int position, String toinsert)
    {
        Integer lastid = listToChange.keySet().stream().collect(Collectors.toList()).get(listToChange.size()-1);
        for (int i = lastid; i>=position; i--)
        {
            listToChange.put(i+1,listToChange.get(i));
        }
        listToChange.put(position,toinsert);

    }
    public void insertIntoLinkedHashMapDraws(LinkedHashMap<Integer, LinkedHashMap<Double, String>> listToChange, int position, LinkedHashMap<Double, String> toinsert)
    {
        if (listToChange.size()==0) return;
        Integer lastid = listToChange.keySet().stream().collect(Collectors.toList()).get(listToChange.size()-1);
        for (int i = lastid; i>=position; i--)
        {
            listToChange.put(i+1,listToChange.get(i));
        }
        listToChange.put(position,toinsert);
    }
    @FunctionalInterface
    public interface ThrowingBiConsumer<T,R, E extends Exception>
    {
        void accept(T o, R o2) throws E;
    }
    @FunctionalInterface
    public interface ThrowingConsumer<T, E extends Exception> {
        void accept(T t) throws E;
    }
    @FunctionalInterface // pour un map par exemple
    public interface ThrowingFunction<T,R, E extends Exception> {
        R apply(T t) throws E;
    }
    @FunctionalInterface
    public interface ThrowingPredicate<T, X extends Throwable> {
        public boolean test(T t) throws X;
    }
    public static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Exception ex) {
                try {
                    E exCast = exceptionClass.cast(ex);
                    System.err.println(
                            "Exception in lambda occured : " + exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }
    public static <T,R, E extends Exception> Function<T,R> handlingFunctionWrapper(ThrowingFunction<T,R, E> throwingConsumer, Class<E> exceptionClass) {
        return i -> {
            try {
                return throwingConsumer.apply(i);
            } catch (Exception ex) {
                try {
                    E exCast = exceptionClass.cast(ex);
                    throw new RuntimeException(exCast);
                } catch (ClassCastException ccEx) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    public static <T, E extends Exception> Predicate<T> handlingPredicateWrapper(ThrowingPredicate<T, E> throwingPredicate, Class<E> exceptionClass) {
        return i -> {
            try {
                return throwingPredicate.test(i);
            } catch (Exception ex) {
                try {
                    E exCast = exceptionClass.cast(ex);
                    throw new RuntimeException(exCast);
                } catch (ClassCastException ccEx) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }





    public static boolean isStringFilled(String string)
    {
        return (string!= null && !string.isEmpty());
    }
    public static String hashMD5Password(String password) throws NoSuchAlgorithmException, IOException
    {
        MessageDigest msgDigest = MessageDigest.getInstance("MD5");
        int i=0;
        while (i<4)
        {
            InputStream inputStream = new ByteArrayInputStream(password.getBytes());
            int l;
            byte[] buffer = new byte[1024];
            while ((l = inputStream.read(buffer)) > -1)
            {
                msgDigest.update(buffer, 0, l);
            }
            inputStream.close();
            password = new String(Base64.getEncoder().encode(msgDigest.digest()));
            i++;
        }
        return password;
    }

    public void writeTextInFileAndCreatePathIfNotExist(String textToWrite, File inFile) throws IOException
    {
        deleteIfFileExistAndCreateFile(inFile);
        try(OutputStreamWriter outputFile = new OutputStreamWriter(new FileOutputStream(inFile), Charset.forName(UTF_8))) {
            outputFile.write(textToWrite);
            outputFile.flush();
        }
    }

    private void deleteIfFileExistAndCreateFile(File inFile) throws IOException {
        inFile.delete();
        File parentDir = new File(inFile.getParent());
        if (!parentDir.exists()) parentDir.mkdirs();
        inFile.createNewFile();
    }

    public void writeByteInFileAndCreatePathIfNotExist(String content, File fileToWrite) throws IOException {
        deleteIfFileExistAndCreateFile(fileToWrite);
        FileWriter f = new FileWriter(fileToWrite);
        byte[] bytes = content.getBytes();
        for (int i = 0; i < bytes.length; i++) f.write(bytes[i]);
        f.close();
    }

    public void deleteDirRecursivelyIfExist(Path pathToBeDeleted) throws IOException {
        if (!new File(pathToBeDeleted.toUri()).exists()) return;
        try (Stream<Path> walk = Files.walk(pathToBeDeleted)) {
            walk.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    public void copyDirRecursively (Path src, Path dest) throws IOException {
        try (Stream<Path> walk = Files.walk(src)) {
            walk.forEach(source -> {
                try {
                    Files.copy(source, dest.resolve(src.relativize(source)),
                            StandardCopyOption.REPLACE_EXISTING); // empleche le throw d'une erreur si dans destination ca existe deja
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public String javaToJson(Object action) {
        return jsonb.toJson(action);
    }

}


