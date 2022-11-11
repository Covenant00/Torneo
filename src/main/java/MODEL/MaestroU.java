/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author root
 */
public class MaestroU {

    private int idMaestro, idE1, idE2, Gana1, Gana2, Empate, idUsuario;

    public MaestroU(int idMaestro, int idE1, int idE2, int Gana1, int Gana2, int Empate, int idUsuario) {
        this.idMaestro = idMaestro;
        this.idE1 = idE1;
        this.idE2 = idE2;
        this.Gana1 = Gana1;
        this.Gana2 = Gana2;
        this.Empate = Empate;
        this.idUsuario = idUsuario;
    }

    public int getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(int idMaestro) {
        this.idMaestro = idMaestro;
    }

    public int getIdE1() {
        return idE1;
    }

    public void setIdE1(int idE1) {
        this.idE1 = idE1;
    }

    public int getIdE2() {
        return idE2;
    }

    public void setIdE2(int idE2) {
        this.idE2 = idE2;
    }

    public int getGana1() {
        return Gana1;
    }

    public void setGana1(int Gana1) {
        this.Gana1 = Gana1;
    }

    public int getGana2() {
        return Gana2;
    }

    public void setGana2(int Gana2) {
        this.Gana2 = Gana2;
    }

    public int getEmpate() {
        return Empate;
    }

    public void setEmpate(int Empate) {
        this.Empate = Empate;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
